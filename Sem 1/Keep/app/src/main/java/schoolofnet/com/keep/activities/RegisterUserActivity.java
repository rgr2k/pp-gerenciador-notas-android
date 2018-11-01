package schoolofnet.com.keep.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lombok.NonNull;
import schoolofnet.com.keep.R;

public class RegisterUserActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    @BindView(R.id.edtEmail)
    EditText editEmail;
    @BindView(R.id.edtPassword)
    EditText editPassword;

    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        ButterKnife.bind(this);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        firebaseAuth = FirebaseAuth.getInstance();

        awesomeValidation.addValidation(this, R.id.edtEmail, Patterns.EMAIL_ADDRESS, R.string.error_valid_email);
        awesomeValidation.addValidation(this, R.id.edtPassword, ".{4,16}", R.string.error_password);

        if (this.firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }

    @OnClick(R.id.do_register_btn)
    public void onClickRegisterBtn(View view) {
        if (!awesomeValidation.validate()) {
            Toast.makeText(getApplicationContext(), "Ops! Something went wrong", Toast.LENGTH_SHORT).show();
        } else {
            this.firebaseAuth.createUserWithEmailAndPassword(editEmail.getText().toString(), editPassword.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> t) {
                            if (t.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }
}
