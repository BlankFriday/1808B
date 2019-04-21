package com.example.lenovo.day3_geek.activity;

import android.widget.Button;
import android.widget.EditText;

import com.example.lenovo.day3_geek.R;
import com.example.lenovo.day3_geek.base.BaseActivity;
import com.example.lenovo.day3_geek.presenter.LoginPresenter;
import com.example.lenovo.day3_geek.utils.ToastUtil;
import com.example.lenovo.day3_geek.view.LoginView;

import butterknife.BindView;
import butterknife.OnClick;
//卫晨旭   1808D
//14272620005181279
public class LoginActivity extends BaseActivity<LoginView,LoginPresenter> implements LoginView {
    @BindView(R.id.bt)
    Button mBt;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_psd)
    EditText etPsd;

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }*/
    @OnClick({R.id.bt})
    public void click(){

       // mPresenter.getData();
        mPresenter.login();
    }

    @Override
    public void setData(String s) {
        mBt.setText(s);
    }

    @Override
    public String getUserName() {
        return etName.getText().toString().trim();
    }

    @Override
    public String getPsd() {
        return etPsd.getText().toString().trim();
    }

    @Override
    public void showToast(String msg) {
        ToastUtil.showShort(msg);
    }
}
