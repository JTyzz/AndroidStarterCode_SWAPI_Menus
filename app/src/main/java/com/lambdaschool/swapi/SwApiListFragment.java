package com.lambdaschool.swapi;

import android.content.Context;
import android.os.Bundle;
import android.os.Trace;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class SwApiListFragment extends Fragment {

    public static final int CATEGORY_PLANET    = 1;
    public static final int CATEGORY_TRANSPORT = 2;

    // TODO: Customize parameter argument names
    public static final String                            ARG_COLUMN_COUNT = "column-count";
    public static final String                            ARG_CATEGORY     = "category";
    // TODO: Customize parameters
    private              int                               mColumnCount     = 1;
    private              int                               category         = 1;
    private              OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SwApiListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static SwApiListFragment newInstance(int columnCount) {
        SwApiListFragment fragment = new SwApiListFragment();
        Bundle            args     = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            category = getArguments().getInt(ARG_CATEGORY);
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_swapiobject_list, container, false);

        final ProgressBar  progressBar  = view.findViewById(R.id.progress_bar);
        final RecyclerView recyclerView = view.findViewById(R.id.list);

        // Set the adapter
        if (recyclerView != null) {
            Context context = view.getContext();
//            final RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            recyclerView.setAdapter(new MySwApiObjectRecyclerViewAdapter(new ArrayList<SwApiObject>(), mListener));

            switch (category) {
                case CATEGORY_PLANET:
                    SwApiDao.getAllPlanets(new AtomicBoolean(false), new SwApiDao.ObjectCallback<ArrayList<Planet>>() {
                        @Override
                        public void returnObject(final ArrayList<Planet> object) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    Trace.beginSection("settingVisibility");
                                    progressBar.setVisibility(View.GONE);
                                    Trace.endSection();

                                    ArrayList<SwApiObject> parentObject = new ArrayList<>();
                                    parentObject.addAll(object);

//                            recyclerView.setAdapter(new MySwApiObjectRecyclerViewAdapter(parentObject, mListener));

                                    ((MySwApiObjectRecyclerViewAdapter) recyclerView.getAdapter()).addItem(parentObject);
                                }
                            });
                        }
                    });
                    break;
                case CATEGORY_TRANSPORT:
                    SwApiDao.getAllTransports(new AtomicBoolean(false), new SwApiDao.ObjectCallback<Transport>() {
                        @Override
                        public void returnObject(final Transport object) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    Trace.beginSection("checkThenSetVisibility");
                                    if (progressBar.getVisibility() != View.GONE) {
                                        progressBar.setVisibility(View.GONE);
                                    }
                                    Trace.endSection();

                            /*ArrayList<SwApiObject> parentObject = new ArrayList<>();
                            parentObject.addAll(object);

                            recyclerView.setAdapter(new MySwApiObjectRecyclerViewAdapter(parentObject, mListener));*/
                                    ((MySwApiObjectRecyclerViewAdapter) recyclerView.getAdapter()).addItem(object);
                                }
                            });
                        }

                        public void returnObject(final ArrayList<Transport> object) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    ArrayList<SwApiObject> parentObject = new ArrayList<>();
                                    parentObject.addAll(object);

                                    recyclerView.setAdapter(new MySwApiObjectRecyclerViewAdapter(parentObject, mListener));
                                }
                            });
                        }
                    });
                    break;
            }
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                                       + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onSwApiObjectListFragmentInteraction(SwApiObject item, View sharedView);
    }
}
