package in.creativelizard.simplevolleyservice.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import in.creativelizard.simplevolleyservice.R;
import in.creativelizard.simplevolleyservice.interfaces.NetworkResponseInterface;
import in.creativelizard.simplevolleyservice.utilities.CallServiceAction;

public class MainActivity extends AppCompatActivity implements NetworkResponseInterface{

    private TextView tvResult;
    private CallServiceAction callServiceAction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        tvResult = findViewById(R.id.tvResult);
        callServiceAction = new CallServiceAction(this);
        callServiceAction.networkResponseInterface = this;
    }

    public void clkGetResponse(View view) {
        callServiceAction.requestVersionApi("getStringDetails.php");
    }

    @Override
    public void onComplete(String response) {
        tvResult.setText(response);
    }

    @Override
    public void onInitialize() {}
}
