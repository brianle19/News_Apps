package thaile.com.aiw_client_finalproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import thaile.com.aiw_client_finalproject.Fragment.FragmentBusiness;
import thaile.com.aiw_client_finalproject.Fragment.FragmentEducation;
import thaile.com.aiw_client_finalproject.Fragment.FragmentHealth;
import thaile.com.aiw_client_finalproject.Fragment.FragmentLawAndLegal;
import thaile.com.aiw_client_finalproject.Fragment.FragmentLove;
import thaile.com.aiw_client_finalproject.Fragment.FragmentScience;
import thaile.com.aiw_client_finalproject.Fragment.FragmentSociety;
import thaile.com.aiw_client_finalproject.Fragment.FragmentSports;
import thaile.com.aiw_client_finalproject.Fragment.FragmentTravel;

/**
 * Created by Thai Le on 11/22/2016.
 */

public class MyPagerAdapter extends FragmentStatePagerAdapter {
    private Fragment f;
    private static final int NUM_PRO = 10;

    public MyPagerAdapter(FragmentManager frm){
        super(frm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                f = new FragmentHome();
                break;
            case 1:
                f = new FragmentSociety();
                break;
            case 2:
                f = new FragmentEducation();
                break;
            case 3:
                f = new FragmentLawAndLegal();
                break;
            case 4:
                f = new FragmentHealth();
                break;
            case 5:
                f = new FragmentBusiness();
                break;
            case 6:
                f = new FragmentScience();
                break;
            case 7:
                f = new FragmentSports();
                break;
            case 8:
                f = new FragmentLove();
                break;
            case 9:
                f = new FragmentTravel();
                break;

        }
        return f;
    }

    @Override
    public int getCount() {
        return NUM_PRO;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0){
            return "Trang chủ";
        } else if (position == 1){
            return "Xã hội";
        } else if (position == 2){
            return "Giáo dục";
        } else if (position == 3){
            return "Pháp luật";
        } else if (position == 4){
            return "Sức khỏe";
        } else if (position == 5){
            return "Kinh doanh";
        } else if (position == 6){
            return "Khoa học - Công nghệ";
        } else if (position == 7){
            return "Thể thao";
        } else if (position == 8){
            return "Tình yêu";
        } else if (position == 9){
            return "Du lịch";
        }
        return super.getPageTitle(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}
