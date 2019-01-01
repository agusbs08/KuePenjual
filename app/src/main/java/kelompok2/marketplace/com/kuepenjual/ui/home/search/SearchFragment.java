package kelompok2.marketplace.com.kuepenjual.ui.home.search;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import kelompok2.marketplace.com.kuepenjual.R;
import kelompok2.marketplace.com.kuepenjual.model.PenjualanBarangList;

public class SearchFragment extends Fragment implements SearchView {

    private ProgressBar pb;
    private RecyclerView recyclerView;
    private EditText etSearch;

    private ArrayList<PenjualanBarangList> listBarangAll;
    private SearchRecyclerViewAdapter adapter;
    private SearchPresenter presenter;

    private ArrayList<PenjualanBarangList> listBarangSearch;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initSearch();
        presenter.getAllBarang();
    }

    private void initSearch(){
        etSearch.requestFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(etSearch, InputMethodManager.SHOW_IMPLICIT);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        etSearch.addTextChangedListener(getTextWatcher());
    }

    private TextWatcher getTextWatcher(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateList(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

    void updateList(CharSequence s){
        showLoading();
        if(listBarangAll.size() > 0){
            listBarangSearch.clear();
            for(PenjualanBarangList penjualanBarangList : listBarangAll){
                if(penjualanBarangList.getBarang().getNama().contains(s)){
                    listBarangSearch.add(penjualanBarangList);
                }
            }
            adapter.notifyDataSetChanged();
        }
        hideLoading();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View view){
        pb = view.findViewById(R.id.pb_fragment_search);
        recyclerView = view.findViewById(R.id.rv_fragment_search);
        etSearch = view.findViewById(R.id.et_search_fragment_search);

        listBarangAll = new ArrayList<>();
        listBarangSearch = new ArrayList<>();
        adapter = new SearchRecyclerViewAdapter(listBarangSearch, getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(adapter);
        presenter = new SearchPresenter(this);
    }

    @Override
    public void showLoading() {
        pb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pb.setVisibility(View.GONE);
    }

    @Override
    public void showListBarang(ArrayList<PenjualanBarangList> listBarang) {
        this.listBarangAll.clear();
        this.listBarangAll.addAll(listBarang);
        updateList("xx");
    }
}
