package schoolofnet.com.keep.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import schoolofnet.com.keep.R;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    @BindView(R.id.edtEmailLogin)
    EditText edtEmailLogin;
    @BindView(R.id.edtPasswordLogin)
    EditText edtPasswordLogin;
//    @BindView(R.id.action_go_to_register)
//    TextView actionGoToRegister;

    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        this.firebaseAuth = FirebaseAuth.getInstance();
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        firebaseAuth = FirebaseAuth.getInstance();

        awesomeValidation.addValidation(this, R.id.edtEmailLogin, Patterns.EMAIL_ADDRESS, R.string.error_valid_email);
        awesomeValidation.addValidation(this, R.id.edtPasswordLogin, ".{4,16}", R.string.error_password);

        if (this.firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }

    @OnClick(R.id.action_go_to_register)
    public void onGoToRegister(View view) {
        Intent registerActivity = new Intent(getApplicationContext(), RegisterUserActivity.class);
        startActivity(registerActivity);
    }

    @OnClick(R.id.do_login_btn)
    public void doLogin(View view) {
        this.firebaseAuth.signInWithEmailAndPassword(edtEmailLogin.getText().toString(),edtPasswordLogin.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
//                        Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                        Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(mainActivity);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Login Error", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
