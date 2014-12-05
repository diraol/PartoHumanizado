package br.com.PartoHumanizado.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.PartoHumanizado.R;
import br.com.PartoHumanizado.fragment.base.BaseFragment;
import br.com.PartoHumanizado.fragment.base.ResStringArrayListFragment;
import bruno.android.utils.adapter.FragmentPageAdapter;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by bruno on 04/12/14.
 */
public class PlanoDePartoFragment extends BaseFragment {

    @InjectView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager, null);
        ButterKnife.inject(this, view);

        FragmentPageAdapter pageAdapter = new FragmentPageAdapter(getChildFragmentManager());

        addFragment(pageAdapter, new TrabalhoDePartoFragment());
        addFragment(pageAdapter, new DuranteParto());
        addFragment(pageAdapter, new PosParto());
        addFragment(pageAdapter, new CuidadosComBebe());
        addFragment(pageAdapter, new CasoCesarea());

        viewPager.setAdapter(pageAdapter);

        return view;
    }

    private void addFragment(FragmentPageAdapter pageAdapter, ResStringArrayListFragment fragment) {
        pageAdapter.addFragment(fragment, fragment.getTitle());
    }

    @Override
    public String getTitle() {
        return "Plano de Parto";
    }


    public static class TrabalhoDePartoFragment extends ResStringArrayListFragment {

        public TrabalhoDePartoFragment() {
            super("Trabalho de Parto", R.array.trabalhoDeParto);
        }
    }

    public static class DuranteParto extends ResStringArrayListFragment {

        public DuranteParto() {
            super("Durante o Parto", R.array.duranteParto);
        }
    }

    public static class PosParto extends ResStringArrayListFragment {

        public PosParto() {
            super("Pós-Parto", R.array.posParto);
        }
    }

    public static class CuidadosComBebe extends ResStringArrayListFragment {

        public CuidadosComBebe() {
            super("Cuidados com o Bebê", R.array.cuidadosComBebe);
        }
    }

    public static class CasoCesarea extends ResStringArrayListFragment {

        public CasoCesarea() {
            super("Caso de Cesárea", R.array.casoCesarea);
        }
    }
}
