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

public class NicknameCheckFragment extends Fragment {

    NicknameCheckCallback mNicknameCheckCallback;

    public static NicknameCheckFragment newInstance() {
        NicknameCheckFragment fragment = new NicknameCheckFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple_content, container, false);
        ((TextView)view.findViewById(R.id.text)).setText("设置昵称");
        Button button = (Button) view.findViewById(R.id.action);
        button.setText("下一步");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mNicknameCheckCallback != null) {
                    mNicknameCheckCallback.onNicknameCheckOk("PrototypeZ");
                }
            }
        });
        return view;
    }

    public void setNicknameCheckCallback(NicknameCheckCallback nicknameCheckCallback) {
        mNicknameCheckCallback = nicknameCheckCallback;
    }

    public interface NicknameCheckCallback {
        void onNicknameCheckOk(String nickname);
    }
}
