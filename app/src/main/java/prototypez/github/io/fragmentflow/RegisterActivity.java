package prototypez.github.io.fragmentflow;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import prototypez.github.io.fragmentflow.entity.User;
import prototypez.github.io.fragmentflow.fragments.NicknameCheckFragment;
import prototypez.github.io.fragmentflow.fragments.PasswordSetFragment;
import prototypez.github.io.fragmentflow.fragments.PhoneRegisterFragment;

public class RegisterActivity extends SequenceContainerActivity {

    public static final int REQUEST_CODE = 2033;

    String phoneNumber;
    String nickName;
    User mUser;

    PhoneRegisterFragment mPhoneRegisterFragment;
    NicknameCheckFragment mNicknameCheckFragment;
    PasswordSetFragment mPasswordSetFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (savedInstanceState != null) {
            mPhoneRegisterFragment = (PhoneRegisterFragment) fragmentManager
                    .findFragmentByTag(PhoneRegisterFragment.class.getCanonicalName());
            mNicknameCheckFragment = (NicknameCheckFragment) fragmentManager
                    .findFragmentByTag(NicknameCheckFragment.class.getCanonicalName());
            mPasswordSetFragment = (PasswordSetFragment) fragmentManager
                    .findFragmentByTag(PasswordSetFragment.class.getCanonicalName());
        } else {
            mPhoneRegisterFragment = new PhoneRegisterFragment();
            mNicknameCheckFragment = new NicknameCheckFragment();
            mPasswordSetFragment = new PasswordSetFragment();
        }

        mPhoneRegisterFragment.setPhoneValidateCallback(new PhoneRegisterFragment.PhoneValidateCallback() {
            @Override
            public void onPhoneValidateOk(String phoneNumber) {
                RegisterActivity.this.phoneNumber = phoneNumber;
                startNicknameCheck();
            }
        });

        mNicknameCheckFragment.setNicknameCheckCallback(new NicknameCheckFragment.NicknameCheckCallback() {
            @Override
            public void onNicknameCheckOk(String nickname) {
                RegisterActivity.this.nickName = nickName;
                startPasswordSet(phoneNumber, nickName);
            }
        });

        mPasswordSetFragment.setRegisterCallback(new PasswordSetFragment.PasswordSetCallback() {
            @Override
            public void onRegisterOk(User user) {
                mUser = user;
                setResult(RESULT_OK);
                finish();
            }
        });

        startPhoneNumberCheck();
    }


    private void startPhoneNumberCheck() {


        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.add(R.id.fragment_container, mPhoneRegisterFragment,
                mPhoneRegisterFragment.getClass().getCanonicalName());

        transaction
                .show(mPhoneRegisterFragment)
                .commit();
    }


    private void startNicknameCheck() {

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.add(R.id.fragment_container, mNicknameCheckFragment, mNicknameCheckFragment.getClass().getCanonicalName());

        transaction
                .hide(fragmentManager.getFragments().get(fragmentManager.getFragments().size() - 1))
                .show(mNicknameCheckFragment)
                .addToBackStack(mNicknameCheckFragment.getClass().getCanonicalName())
                .commit();


    }


    private void startPasswordSet(String phoneNumber, String nickName) {

        mPasswordSetFragment.setArguments(phoneNumber, nickName);

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.add(R.id.fragment_container, mPasswordSetFragment, PasswordSetFragment.class.getCanonicalName());

        transaction
                .hide(fragmentManager.getFragments().get(fragmentManager.getFragments().size() - 1))
                .show(mPasswordSetFragment)
                .addToBackStack(mPasswordSetFragment.getClass().getCanonicalName())
                .commit();
    }




}
