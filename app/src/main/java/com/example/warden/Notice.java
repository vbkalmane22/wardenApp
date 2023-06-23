package com.example.warden;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class Notice extends AppCompatActivity {
    Uri pdfuri = null;
    Button upload_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        upload_btn = findViewById(R.id.upload_btn);

        upload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

    }

    private void openGallery() {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        activityResultLauncher.launch(intent);

    }

    public ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult activityResult) {
                    int result = activityResult.getResultCode();
                    Intent data = activityResult.getData();

                    FirebaseStorage storage = FirebaseStorage.getInstance("gs://sahayadri-app.appspot.com");
                    StorageReference storageRef = storage.getReference();

                    if(result == RESULT_OK){

                        ProgressBar progressBar = findViewById(R.id.progressBar);
                        progressBar.setVisibility(View.VISIBLE);

                        pdfuri = data.getData();
                        final String timestamp = "" + System.currentTimeMillis();
                        final String messagePushID = timestamp;

                        final StorageReference filepath = storageRef.child(messagePushID + "." + "pdf");

                        Toast.makeText(Notice.this, filepath.getName(), Toast.LENGTH_SHORT).show();

                        filepath.putFile(pdfuri).continueWithTask(new Continuation() {
                            @Override
                            public Object then(@NonNull Task task) throws Exception {
                                if (!task.isSuccessful()) {
                                    throw task.getException();
                                }
                                return filepath.getDownloadUrl();
                            }
                        }).addOnCompleteListener((OnCompleteListener<Uri>) task -> {
                            if ((task.isSuccessful())) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(Notice.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(Notice.this, "UploadedFailed", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                    else{
                        Toast.makeText(Notice.this,"FAILED",Toast.LENGTH_LONG).show();
                    }
                }
            }
    );
}