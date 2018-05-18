package prototypez.github.io.fragmentflow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public abstract class SequenceContainerActivity extends AppCompatActivity {

    FrameLayout sequenceContainer;
    protected FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sequenceContainer = new FrameLayout(this);
        sequenceContainer.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                )
        );
        sequenceContainer.setId(R.id.fragment_container);
        setContentView(sequenceContainer);

        fragmentManager = getSupportFragmentManager();
    }


    protected void push(FragmentProvider fragmentProvider, String tag) {

        if (tag == null) return;

        Fragment fragmentInMemory = fragmentManager
                .findFragmentByTag(tag);

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (fragmentInMemory == null) {
            fragmentInMemory = fragmentProvider.provide();
            transaction.add(fragmentInMemory, tag);
        }

        transaction
                .show(fragmentInMemory)
                .addToBackStack(tag)
                .commit();

    }

    public interface FragmentProvider {
        Fragment provide();
    }

}
