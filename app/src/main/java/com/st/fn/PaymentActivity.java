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
    EditText CardNumber,CVC,ExpiryDate,ExpireYear;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
       addListenerOnButton();
    }
    public void addListenerOnButton() {

        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
         CardNumber=(EditText)findViewById(R.id.editText5);
                String str1=CardNumber.getText().toString();
             ExpiryDate = (EditText)findViewById(R.id.editText9);
                String str2=ExpiryDate.getText().toString();
                int Month=Integer.parseInt(str2);

             ExpireYear=(EditText)findViewById(R.id.editText10);
                String str3=ExpireYear.getText().toString();
                int Year=Integer.parseInt(str3);
              CVC=(EditText)findViewById(R.id.editText8);
                String str4=CVC.getText().toString();
                Card card = new Card(str1, Month,Year, str4);
                if ( !card.validateCard() ) {
                    // Show errors
                    //System.out.print("error card");
                    Log.d("er","error card");
                }
                 if(!card.validateExpMonth())
                 {
                     Log.d("mn","error in month");
                 }
                if(!card.validateExpYear())
                {
                    Log.d("yy","Error in year");
                }
                if(!card.validateCVC())
                {
                    Log.d("cc","Error in cvc");
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
