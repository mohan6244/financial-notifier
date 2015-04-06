package com.st.fn;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;

public class PaymentActivity extends ActionBarActivity {
    private Button mButton;
    EditText CardNumber,CVC,ExpiryDate;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
       addListenerOnButton();
    }
    public void addListenerOnButton() {

        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                ExpiryDate = (EditText)findViewById(R.id.editText9);

                Card card = new Card("4242-4242-4242-4242", 12, 2016, "123");
                if ( !card.validateCard() ) {
                    // Show errors
                    System.out.print("error card");
                }

                // "pk_test_wFckEVqq1fJ8EzDd8FVpEMZy"
                boolean validation = card.validateCard();
                if(validation) {
                    if (validation) {
                        new Stripe().createToken(
                                card,
                                "pk_test_wFckEVqq1fJ8EzDd8FVpEMZy",
                                new TokenCallback() {
                                    public void onSuccess(Token token) {
                                        Log.d("suc", "Success");
                                    }
                                    public void onError(Exception error) {
                                        Log.d("err", "Error");
                                    }
                                });
                    }
                }




            }
        });
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_payment, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
