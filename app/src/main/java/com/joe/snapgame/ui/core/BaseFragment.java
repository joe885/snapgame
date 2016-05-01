package com.joe.snapgame.ui.core;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.joe.snapgame.utils.DataError;

/**
 * Created by Joseph Donegan.
 */
public abstract class BaseFragment extends InjectableFragment implements IBaseView {
    private static final int MINIMUM_DIALOG_DISPLAY_TIME_MS = 500;

    private ProgressDialog progressDialog;
    private Handler dialogHandler;
    private long dialogDisplayTime;

    public boolean handleBackPressed() {
        return false;
    }

    public void onDestroy() {
        if (dialogHandler != null) {
            dialogHandler.removeCallbacks(showDialogRunnable);
            dialogHandler.removeCallbacks(hideDialogRunnable);
            dialogHandler = null;
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            progressDialog = null;
        }
        super.onDestroy();
    }

    @Override
    public void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setCancelable(false);
        }
        if (dialogHandler == null) {
            dialogHandler = new Handler(Looper.getMainLooper());
        }
        dialogHandler.post(showDialogRunnable);
    }

    @Override
    public void hideProgressDialog() {
        long displayedTime = System.currentTimeMillis() - dialogDisplayTime;
        long remainingTime = Math.max(0, MINIMUM_DIALOG_DISPLAY_TIME_MS - displayedTime);
        dialogHandler.postDelayed(hideDialogRunnable, remainingTime);
    }

    @Override
    public void showError(DataError error) {
        String errorMessage = error.getErrorMessage(getResources());
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(@StringRes int resId) {
        Toast.makeText(getContext(), resId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private final Runnable showDialogRunnable = new Runnable() {
        @Override
        public void run() {
            dialogDisplayTime = System.currentTimeMillis();
            progressDialog.show();
        }
    };

    private final Runnable hideDialogRunnable = new Runnable() {
        @Override
        public void run() {
            progressDialog.dismiss();
        }
    };
}
