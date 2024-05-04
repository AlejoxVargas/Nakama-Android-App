package com.finalproject.nakamaandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.stripe.android.paymentsheet.PaymentSheet;
import com.stripe.android.paymentsheet.PaymentSheetResult;

import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.stripe.android.paymentsheet.PaymentSheet;

import java.util.HashMap;
import java.util.Map;

public class SubscriptionActivity extends AppCompatActivity {

    Spinner sp_Pagos;
    RadioGroup rgPlanes, rgMetodoPago;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button btnSubscription, btnOmmision;
    private static final String TAG = "SubscriptionActivity";

    //Payment Method Stripe
    private static final String TAGStripe = "CheckoutActivity";
    private static final String BACKEND_URL = "http://10.0.2.2:4242";

    private String paymentIntentClientSecret;
    private PaymentSheet paymentSheet;

    private Button payButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);

        rgPlanes = findViewById(R.id.rg_planes);
        sp_Pagos = findViewById(R.id.sp_pagos);
        rgMetodoPago = findViewById(R.id.rg_metodo_pago);

        btnSubscription = findViewById(R.id.btn_subscription);
        btnOmmision = findViewById(R.id.btn_omitir);


        btnSubscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verificar si algún RadioButton está seleccionado antes de obtener el texto
                int selectedPlanId = rgPlanes.getCheckedRadioButtonId();
                int selectedMetodoPagoId = rgMetodoPago.getCheckedRadioButtonId();

                if (selectedPlanId == -1 || selectedMetodoPagoId == -1) {
                    // Mostrar mensaje de error si algún RadioButton no está seleccionado
                    Toast.makeText(SubscriptionActivity.this, "Debe seleccionar todos los campos", Toast.LENGTH_SHORT).show();
                    return; // Salir del método onClick sin continuar
                }

                // Obtener el texto de los RadioButton seleccionados
                RadioButton rbPlan = findViewById(selectedPlanId);
                String plan = rbPlan.getText().toString();


                RadioButton rbMetodoPago = findViewById(selectedMetodoPagoId);
                String metodoPago = rbMetodoPago.getText().toString();

                String mensualidad = sp_Pagos.getSelectedItem().toString();

                // Todos los campos están rellenos, proceder con la inserción en la base de datos
                createData(plan, mensualidad, metodoPago);
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.pagos, android.R.layout.simple_spinner_dropdown_item);
        sp_Pagos.setAdapter(adapter);


        //Payment method stripe variables
        // Hook up the pay button
        payButton = findViewById(R.id.btn_subscription);
        payButton.setOnClickListener(this::onPayClicked);
        payButton.setEnabled(false);

        paymentSheet = new PaymentSheet(this, this::onPaymentSheetResult);

        fetchPaymentIntent();

        btnOmmision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubscriptionActivity.this, CreateProfileActivity.class);
                startActivity(intent);
            }
        });
    }

   public void createData(String plan, String mensualidad, String metodoPago) {
        Map<String, Object> subscription = new HashMap<>();
        subscription.put("tipo_plan", plan);
        subscription.put("mensualidad", mensualidad);
        subscription.put("metodo_pago", metodoPago);

        db.collection("subscription")
                .add(subscription).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        Toast.makeText(SubscriptionActivity.this, "Creado", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                        Toast.makeText(SubscriptionActivity.this, "error", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showAlert(String title, @Nullable String message) {
        runOnUiThread(() -> {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton("Ok", null)
                    .create();
            dialog.show();
        });
    }

    private void showToast(String message) {
        runOnUiThread(() -> Toast.makeText(this, message, Toast.LENGTH_LONG).show());
    }

    public void fetchPaymentIntent() {
        final String shoppingCartContent = "{\"items\": [ {\"id\":\"xl-tshirt\"}]}";

        final RequestBody requestBody = RequestBody.create(
                shoppingCartContent,
                MediaType.get("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url(BACKEND_URL + "/create-payment-intent")
                .post(requestBody)
                .build();

        new OkHttpClient()
                .newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        showAlert("Failed to load data", "Error: " + e.toString());
                    }

                    @Override
                    public void onResponse(
                            @NonNull Call call,
                            @NonNull Response response
                    ) throws IOException {
                        if (!response.isSuccessful()) {
                            showAlert(
                                    "Failed to load page",
                                    "Error: " + response.toString()
                            );
                        } else {
                            final JSONObject responseJson = parseResponse(response.body());
                            paymentIntentClientSecret = responseJson.optString("clientSecret");
                            runOnUiThread(() -> payButton.setEnabled(true));
                            Log.i(TAG, "Retrieved PaymentIntent");
                        }
                    }
                });
    }

    private JSONObject parseResponse(ResponseBody responseBody) {
        if (responseBody != null) {
            try {
                return new JSONObject(responseBody.string());
            } catch (IOException | JSONException e) {
                Log.e(TAGStripe, "Error parsing response", e);
            }
        }

        return new JSONObject();
    }

    private void onPayClicked(View view) {
        PaymentSheet.Configuration configuration = new PaymentSheet.Configuration("Example, Inc.");

        // Present Payment Sheet
        paymentSheet.presentWithPaymentIntent(paymentIntentClientSecret, configuration);
    }

    private void onPaymentSheetResult(
            final PaymentSheetResult paymentSheetResult
    ) {
        if (paymentSheetResult instanceof PaymentSheetResult.Completed) {
            showToast("Payment complete!");
        } else if (paymentSheetResult instanceof PaymentSheetResult.Canceled) {
            Log.i(TAG, "Payment canceled!");
        } else if (paymentSheetResult instanceof PaymentSheetResult.Failed) {
            Throwable error = ((PaymentSheetResult.Failed) paymentSheetResult).getError();
            showAlert("Payment failed", error.getLocalizedMessage());
        }
    }
}