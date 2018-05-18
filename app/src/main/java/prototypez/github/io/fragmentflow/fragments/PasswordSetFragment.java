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

public class PasswordSetFragment extends Fragment {

    PasswordSetCallback mPasswordSetCallback;

    String phone;
    String nickname;

    public void setArguments(String phone, String nickname) {
        Bundle bundle = new Bundle();
        bundle.putString("phone", phone);
        bundle.putString("nickname", nickname);
        setArguments(bundle);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            phone = getArguments().getString("phone");
            nickname = getArguments().getString("nickname");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple_content, container, false);
        ((TextView)view.findViewById(R.id.text)).setText("设置密码");
        Button button = (Button) view.findViewById(R.id.action);
        button.setText("完成");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPasswordSetCallback != null) {
                    User user = new User();
                    user.nickname = nickname;
                    user.phoneNumber = phone;
                    mPasswordSetCallback.onRegisterOk(user);
                }
            }
        });
        return view;
    }

    public void setRegisterCallback(PasswordSetCallback passwordSetCallback) {
        mPasswordSetCallback = passwordSetCallback;
    }

    public interface PasswordSetCallback {
        void onRegisterOk(User user);
    }
}
