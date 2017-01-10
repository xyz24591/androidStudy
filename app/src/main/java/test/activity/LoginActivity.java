package test.activity;

import android.os.Bundle;

import com.style.base.BaseActivity;
import com.style.bean.Friend;
import com.style.bean.User;
import com.style.db.custom.UserDBManager;
import com.style.framework.R;
import com.style.manager.AccountManager;

import java.util.List;

import test.home.MainActivity;

public class LoginActivity extends BaseActivity {


    private User curUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mLayoutResID = R.layout.activity_login;
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        login();
    }

    public void login() {

        curUser = AccountManager.getInstance().getCurrentUser();
        if (curUser == null) {
            User user = new User(8, "18202823096", "123456", "夏军", "1234567890");
            AccountManager.getInstance().setCurrentUser(user);
        }
        curUser = AccountManager.getInstance().getCurrentUser();

        synData();
    }

    public void synData() {

        List<User> friends = UserDBManager.getInstance().queryAllMyFriend(curUser.getUserId());
        /*if (friends != null && friends.size() > 0) {
            skip(MainActivity.class);
            finish();
        } else {
            for (int i = 1; i < 10; i++) {
                if (i != 8) {
                    User user = new User(i, "phone" + i, "123456", "用户" + i, null);
                    UserDBManager.getInstance().insertUser(user);
                }
            }

            for (int i = 1; i < 10; i++) {
                Friend bean = new Friend();
                bean.setFriendId(i);
                if (i < 5)
                    bean.setOwnerId(8);
                else
                    bean.setOwnerId(4);
                UserDBManager.getInstance().insertFriend(bean);
            }

            skip(MainActivity.class);
            finish();
        }*/

    }
}