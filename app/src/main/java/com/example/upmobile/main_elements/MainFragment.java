package com.example.upmobile.main_elements;

import androidx.fragment.app.Fragment;
import com.example.upmobile.databinding.MainScreenFragmetBinding;
import com.example.upmobile.interfaces.IMenuClickListener;
import com.example.upmobile.main_elements.models.MenuViewModel;

public class MainFragment extends Fragment implements IMenuClickListener {
    MainScreenFragmetBinding binding;
    MainAdapter mainAdapter;

    @Override
    public void onMenuClickFunc(MenuViewModel model) {


    }
}
