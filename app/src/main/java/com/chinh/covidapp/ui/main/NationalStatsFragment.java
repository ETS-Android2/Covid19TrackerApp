package com.chinh.covidapp.ui.main;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.chinh.covidapp.R;
import com.chinh.covidapp.util.themeUtils;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link NationalStats.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link NationalStats#newInstance} factory method to
// * create an instance of this fragment.
// */
public class NationalStatsFragment extends Fragment implements AdapterView.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    //  national link: https://api.covidtracking.com/v1/states/daily.json
        // old link: https://api.covid19india.org/data.json
    //test link: https://disease.sh/v3/covid-19/all
    private static final String URL= "https://disease.sh/v3/covid-19/all";
    private ArrayList<String> date;
    private ArrayList<String> arrayList_season;
    private ArrayList<String> arrayAdapter_season;
    private Integer value=1000;
    private TextView totalCases,timeStamp;
    private LinearLayout linearLayout;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private LineChart mChart;
    private TextView tvCountryName,todayCases,todayDeaths,todayRecovered;
    private ImageView imageFlag;
    private Spinner spinnerText;
    private BarChart barChart,barChart2,barChart3;
    private TextInputLayout til_season;
    private AutoCompleteTextView act_seasons;
    public NationalStatsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NationalStats.
     */
    // TODO: Rename and change types and number of parameters
    public static NationalStatsFragment newInstance(String param1, String param2) {
        NationalStatsFragment fragment = new NationalStatsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        themeUtils.onFragmentCreateSetTheme(getActivity());


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_national_stats, container, false);

//        if (getArguments() != null) {
//            value = getArguments().getInt("Theme",999);
//        }
        spinnerText = view.findViewById(R.id.spinnerText);
        // Creating an Array Adapter to populate the spinner with the data in the string resources
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getContext(),R.array.list_countries
                ,R.layout.color_spinner_layout);
        // Specify the layout to use when the list of choices appears
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerText.setAdapter(spinnerAdapter);
        spinnerText.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                extractNew(selectedItem);
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {
                extractNew("Vietnam");
            }
        });
        date=new ArrayList<String>();
        imageFlag = view.findViewById(R.id.imageFlag);
        todayCases = view.findViewById(R.id.todayCases);
        todayDeaths = view.findViewById(R.id.todayDeaths);
        todayRecovered = view.findViewById(R.id.todayRecovered);
        barChart=view.findViewById(R.id.bar_chart);
        barChart.setDrawGridBackground(false);
        barChart.setNoDataText("Loading...");
        barChart.setNoDataTextColor(Color.WHITE);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getDescription().setEnabled(false);
        barChart.getAxisLeft().setEnabled(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getAxisRight().setDrawGridLines(false);
        barChart.setAutoScaleMinMaxEnabled(true);
        barChart.setDoubleTapToZoomEnabled(false);
        YAxis yAxisa = barChart.getAxisRight();
        yAxisa.setAxisMinimum(0f);
        YAxis yAxisla=barChart.getAxisLeft();
        yAxisla.setAxisMinimum(0f);

        barChart2=view.findViewById(R.id.bar_chart2);
        barChart2.setDrawGridBackground(false);
        barChart2.setNoDataText("Loading...");
        barChart2.setNoDataTextColor(Color.WHITE);
        barChart2.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart2.getDescription().setEnabled(false);
        barChart2.getAxisLeft().setEnabled(false);
        barChart2.getAxisLeft().setDrawGridLines(false);
        barChart2.getXAxis().setDrawGridLines(false);
        barChart2.getAxisRight().setDrawGridLines(false);
        barChart2.setAutoScaleMinMaxEnabled(true);

//        barChart2.setTouchEnabled(false);
        barChart2.setDoubleTapToZoomEnabled(false);
        YAxis yAxis = barChart2.getAxisRight();
        yAxis.setAxisMinimum(0f);
        YAxis yAxisl=barChart2.getAxisLeft();
        yAxisl.setAxisMinimum(0f);

        barChart3=view.findViewById(R.id.bar_chart3);
        barChart3.setDrawGridBackground(false);
        barChart3.setNoDataText("Loading...");
        barChart3.setNoDataTextColor(Color.WHITE);
        barChart3.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart3.getDescription().setEnabled(false);
        barChart3.getAxisLeft().setEnabled(false);
        barChart3.getAxisLeft().setDrawGridLines(false);
        barChart3.getXAxis().setDrawGridLines(false);
        barChart3.getAxisRight().setDrawGridLines(false);
        barChart3.setAutoScaleMinMaxEnabled(true);
        barChart3.setDoubleTapToZoomEnabled(false);
        YAxis yAxisb = barChart3.getAxisRight();
        yAxisb.setAxisMinimum(0f);
        YAxis yAxisbl=barChart3.getAxisLeft();
        yAxisbl.setAxisMinimum(0f);


        String text = spinnerText.getSelectedItem().toString();
        extractNew(text);

        Legend ll = barChart.getLegend();
        ll.setForm(Legend.LegendForm.LINE);
        Legend lll = barChart2.getLegend();
        lll.setForm(Legend.LegendForm.LINE);
        Legend l3 = barChart3.getLegend();
        l3.setForm(Legend.LegendForm.LINE);
//        mChart.getAxisRight().setTextColor(Color.WHITE);
//        mChart.getXAxis().setTextColor(Color.WHITE);



        ll.setTextColor(Color.GRAY);
        lll.setTextColor(Color.GRAY);
        l3.setTextColor(Color.GRAY);
        barChart.setNoDataTextColor(Color.GRAY);
        barChart.getAxisRight().setTextColor(Color.GRAY);
        barChart.getXAxis().setTextColor(Color.GRAY);

        barChart2.setNoDataTextColor(Color.GRAY);
        barChart2.getAxisRight().setTextColor(Color.GRAY);
        barChart2.getXAxis().setTextColor(Color.GRAY);

        barChart3.setNoDataTextColor(Color.GRAY);
        barChart3.getAxisRight().setTextColor(Color.GRAY);
        barChart3.getXAxis().setTextColor(Color.GRAY);

////test
//        if(value%2==0){
//            mChart.setNoDataTextColor(Color.BLACK);
//            mChart.getAxisRight().setTextColor(Color.BLACK);
//            mChart.getXAxis().setTextColor(Color.BLACK);
//            l.setTextColor(Color.BLACK);
//            ll.setTextColor(Color.BLACK);
//            lll.setTextColor(Color.BLACK);
//            l3.setTextColor(Color.BLACK);
//            barChart.setNoDataTextColor(Color.BLACK);
//            barChart.getAxisRight().setTextColor(Color.BLACK);
//            barChart.getXAxis().setTextColor(Color.BLACK);
//
//            barChart2.setNoDataTextColor(Color.BLACK);
//            barChart2.getAxisRight().setTextColor(Color.BLACK);
//            barChart2.getXAxis().setTextColor(Color.BLACK);
//
//            barChart3.setNoDataTextColor(Color.BLACK);
//            barChart3.getAxisRight().setTextColor(Color.BLACK);
//            barChart3.getXAxis().setTextColor(Color.BLACK);
//
//        }
//        else{
//            mChart.setNoDataTextColor(Color.WHITE);
//            mChart.getAxisRight().setTextColor(Color.WHITE);
//            mChart.getXAxis().setTextColor(Color.WHITE);
//            l.setTextColor(Color.WHITE);
//            ll.setTextColor(Color.WHITE);
//            lll.setTextColor(Color.WHITE);
//            l3.setTextColor(Color.WHITE);
//            barChart.setNoDataTextColor(Color.WHITE);
//            barChart.getAxisRight().setTextColor(Color.WHITE);
//            barChart.getXAxis().setTextColor(Color.WHITE);
//F
//            barChart2.setNoDataTextColor(Color.WHITE);
//            barChart2.getAxisRight().setTextColor(Color.WHITE);
//            barChart2.getXAxis().setTextColor(Color.WHITE);
//
//            barChart3.setNoDataTextColor(Color.WHITE);
//            barChart3.getAxisRight().setTextColor(Color.WHITE);
//            barChart3.getXAxis().setTextColor(Color.WHITE);
//        }



        return view;
    }
    private void extractNew(String selectedItem){
        extractCountries(selectedItem);
    }
    private void extractCountries(String shortName) {
        String DefaultURL = "https://disease.sh/v3/covid-19/countries/"+shortName;
        StringRequest request = new StringRequest(Request.Method.GET, DefaultURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response.toString());
                    String countryName = jsonObject.getString("country");
                    String today_cases = jsonObject.getString("todayCases");
                    String today_deaths = jsonObject.getString("todayDeaths");
                    String today_recovered = jsonObject.getString("todayRecovered");
                    String cases = jsonObject.getString("cases");
                    JSONObject object = jsonObject.getJSONObject("countryInfo");
                    String flagUrl = object.getString("flag");
                    Picasso.get().load(flagUrl).into(imageFlag);
                    todayCases.setText(today_cases);
                    todayDeaths.setText(today_deaths);
                    todayRecovered.setText(today_recovered);

                    extractData(countryName);
                }catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        RequestQueue requestQ = Volley.newRequestQueue(getActivity());
        requestQ.add(request);
    }
        private void extractData (String countries){

            String URLconfirm = "https://api.covid19api.com/country/" + countries + "/status/confirmed?from=2021-07-01T00:00:00Z&to=today";
            String URLdeaths = "https://api.covid19api.com/country/" + countries + "/status/deaths?from=2021-07-01T00:00:00Z&to=today";
            String URLrecover = "https://api.covid19api.com/country/" + countries + "/status/recovered?from=2021-07-01T00:00:00Z&to=today";


            StringRequest request2 = new StringRequest(Request.Method.GET, URLconfirm, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {

                        JSONArray jsonArray = new JSONArray(response);
                        ArrayList<BarEntry> byVals = new ArrayList<>();

                        int j = -1;
                        for (int i = 0; i < jsonArray.length(); i++) {
                            j++;
                            JSONObject currentDate = jsonArray.getJSONObject(i);
                            String x = currentDate.getString("Date");
                            date.add(x.substring(0, 10));
                            byVals.add(new BarEntry(j, currentDate.getInt("Cases")));
                        }
                        final ArrayList<String> xVals = date;
                        BarDataSet dataset = new BarDataSet(byVals, "DailyCases");
                        dataset.setColor(Color.rgb(255, 51, 51));

                        if (value % 2 != 0) {
                            dataset.setValueTextColor(Color.WHITE);
                        }


                        ArrayList<IBarDataSet> barDataSets = new ArrayList<>();
                        barDataSets.add(dataset);

                        BarData barData = new BarData(barDataSets);

                        XAxis bxAxis = barChart.getXAxis();
                        bxAxis.setValueFormatter(new ValueFormatter() {
                            @Override
                            public String getFormattedValue(float value, AxisBase axis) {
                                return xVals.get((int) value);
                            }
                        });


                        barChart.setData(barData);
                        IMarker marker1 = new MyMarkerView(getContext(), R.layout.custom_marker_view, xVals);
                        barChart.setMarker(marker1);


                        barChart.invalidate();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }
            );

            StringRequest request3 = new StringRequest(Request.Method.GET, URLdeaths, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        ArrayList<BarEntry> byVals1 = new ArrayList<>();
                        int j = -1;
                        for (int i = 0; i < jsonArray.length(); i++) {
                            j++;
                            JSONObject currentDate = jsonArray.getJSONObject(i);
                            String x = currentDate.getString("Date");
                            date.add(x.substring(0, 10));
                            byVals1.add(new BarEntry(j, currentDate.getInt("Cases")));
                        }
                        final ArrayList<String> xVals = date;

                        BarDataSet dataSet1 = new BarDataSet(byVals1, "DailyRecovered");
                        dataSet1.setColor(Color.rgb(78, 228, 78));

                        if (value % 2 != 0) {
                            dataSet1.setValueTextColor(Color.WHITE);
                        }

                        ArrayList<IBarDataSet> barDataSets1 = new ArrayList<>();
                        barDataSets1.add(dataSet1);
                        BarData barData1 = new BarData(barDataSets1);


                        XAxis bxAxis1 = barChart2.getXAxis();
                        bxAxis1.setValueFormatter(new ValueFormatter() {
                            @Override
                            public String getFormattedValue(float value, AxisBase axis) {
                                return xVals.get((int) value);
                            }
                        });


                        barChart2.setData(barData1);
                        IMarker marker2 = new MyMarkerView(getContext(), R.layout.custom_marker_view, xVals);
                        barChart2.setMarker(marker2);

                        barChart2.invalidate();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }
            );

            StringRequest request4 = new StringRequest(Request.Method.GET, URLrecover, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        ArrayList<BarEntry> byVals2 = new ArrayList<>();
                        int j = 0;
                        for (int i = 0; i < jsonArray.length(); i++) {
                            j++;
                            JSONObject currentDate = jsonArray.getJSONObject(i);
                            String x = currentDate.getString("Date");

                            date.add(x.substring(0, 10));
                            byVals2.add(new BarEntry(j, currentDate.getInt("Cases")));
                        }
                        final ArrayList<String> xVals = date;
                        BarDataSet dataSet2 = new BarDataSet(byVals2, "DailyDeceased");
                        dataSet2.setColor(Color.rgb(28, 28, 240));
                        if (value % 2 != 0) {
                            dataSet2.setValueTextColor(Color.WHITE);
                        }
                        ArrayList<IBarDataSet> barDataSets2 = new ArrayList<>();
                        barDataSets2.add(dataSet2);
                        BarData barData2 = new BarData(barDataSets2);

                        XAxis bxAxis2 = barChart3.getXAxis();
                        bxAxis2.setValueFormatter(new ValueFormatter() {
                            @Override
                            public String getFormattedValue(float value, AxisBase axis) {
                                return xVals.get((int) value);
                            }
                        });


                        barChart3.setData(barData2);
                        IMarker marker3 = new MyMarkerView(getContext(), R.layout.custom_marker_view, xVals);
                        barChart3.setMarker(marker3);


                        barChart3.invalidate();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }
            );
            RequestQueue requestQ = Volley.newRequestQueue(getActivity());
            requestQ.add(request2);
            requestQ.add(request3);
            requestQ.add(request4);
        }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String text = spinnerText.getSelectedItem().toString();
        extractNew(text);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}