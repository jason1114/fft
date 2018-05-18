package prototypez.github.io.fragmentflow.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import prototypez.github.io.fragmentflow.R;
import prototypez.github.io.fragmentflow.entity.User;

public class PhoneRegisterFragment extends Fragment {

    PhoneValidateCallback mPhoneValidateCallback;

    public static PhoneRegisterFragment newInstance() {
        PhoneRegisterFragment fragment = new PhoneRegisterFragment();

        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple_content, container, false);
        ((TextView)view.findViewById(R.id.text)).setText("验证手机号");
        Button button = view.findViewById(R.id.action);
        button.setText("下一步");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPhoneValidateCallback != null) {
                    mPhoneValidateCallback.onPhoneValidateOk("17317512926");
                }
            }
        });
        return view;
    }

    public void setPhoneValidateCallback(PhoneValidateCallback phoneValidateCallback) {
        mPhoneValidateCallback = phoneValidateCallback;
    }

    public interface PhoneValidateCallback {
        void onPhoneValidateOk(String phoneNumber);
    }
}
