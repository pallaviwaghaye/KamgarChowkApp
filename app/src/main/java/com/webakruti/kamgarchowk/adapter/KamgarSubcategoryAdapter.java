package com.webakruti.kamgarchowk.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.kamgarUI.KamgarSubscriptionPlanActivity;
import com.webakruti.kamgarchowk.model.KamgarPrice;
import com.webakruti.kamgarchowk.model.KamgarSubcategoriesResponse;
import com.webakruti.kamgarchowk.model.KamgarUpdateStatus;
import com.webakruti.kamgarchowk.retrofit.ApiConstants;
import com.webakruti.kamgarchowk.retrofit.service.RestClient;
import com.webakruti.kamgarchowk.userUI.KamgarListActivity;
import com.webakruti.kamgarchowk.utils.NetworkUtil;
import com.webakruti.kamgarchowk.utils.SharedPreferenceManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KamgarSubcategoryAdapter extends RecyclerView.Adapter<KamgarSubcategoryAdapter.ViewHolder> {

    private Activity context;
    private int size;
    List<KamgarSubcategoriesResponse.Subcategory> list;
    Button buttonDoneSubcategory;
    List<Boolean> listOfChecks;
    RecyclerView recyclerView;
    public HashMap<Integer, KamgarSubcategoryAdapter.ViewHolder> holderHashMap = new HashMap<>();
    int categoryId;
    private ProgressDialog progressDialogForAPI;

    public KamgarSubcategoryAdapter(Activity context, List<KamgarSubcategoriesResponse.Subcategory> list, RecyclerView recyclerView, int categoryId) {
        this.context = context;
        this.list = list;
        buttonDoneSubcategory = context.findViewById(R.id.buttonDoneSubcategory);
        this.recyclerView = recyclerView;
        this.categoryId = categoryId;
        listOfChecks = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            listOfChecks.add(i, false);
        }
    }

    @NonNull
    @Override
    public KamgarSubcategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_kamgar_subcategory, viewGroup, false);
        KamgarSubcategoryAdapter.ViewHolder viewHolder = new KamgarSubcategoryAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final KamgarSubcategoryAdapter.ViewHolder viewHolder, final int position) {


        final KamgarSubcategoriesResponse.Subcategory kamgarSubcategory = list.get(position);
        //viewHolder.textViewCategory.setText("Category " + position);

        viewHolder.checkboxSubcategoryName.setText(kamgarSubcategory.getName());

       /* viewHolder.edittextHourlyPrice.setText(kamgarSubcategory.getHourly()+"");
        viewHolder.edittextHalfdayPrice.setText(kamgarSubcategory.getHalfday()+"");
        viewHolder.edittextFulldayPrice.setText(kamgarSubcategory.getFullday()+"");
        viewHolder.edittextWeeklyPrice.setText(kamgarSubcategory.getWeekly()+"");
        viewHolder.edittextMonthlyPrice.setText(kamgarSubcategory.getMonthly()+"");*/


        setDataPrefilled(kamgarSubcategory, viewHolder, position);


        viewHolder.checkboxSubcategoryName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    listOfChecks.set(position, true);
                    viewHolder.edittextFulldayPrice.setEnabled(true);
                    viewHolder.edittextHalfdayPrice.setEnabled(true);
                    viewHolder.edittextHourlyPrice.setEnabled(true);
                    viewHolder.edittextMonthlyPrice.setEnabled(true);
                    viewHolder.edittextWeeklyPrice.setEnabled(true);

                } else {
                    listOfChecks.set(position, false);
                    viewHolder.edittextFulldayPrice.setEnabled(false);
                    viewHolder.edittextHalfdayPrice.setEnabled(false);
                    viewHolder.edittextHourlyPrice.setEnabled(false);
                    viewHolder.edittextMonthlyPrice.setEnabled(false);
                    viewHolder.edittextWeeklyPrice.setEnabled(false);

                    viewHolder.edittextFulldayPrice.setText("");
                    viewHolder.edittextHalfdayPrice.setText("");
                    viewHolder.edittextHourlyPrice.setText("");
                    viewHolder.edittextMonthlyPrice.setText("");
                    viewHolder.edittextWeeklyPrice.setText("");
                }
            }
        });

        if (kamgarSubcategory.getHourly() == 0) {
            viewHolder.edittextHourlyPrice.setText("");
        } else {
            viewHolder.edittextHourlyPrice.setText(kamgarSubcategory.getHourly() + "");
        }

        if (kamgarSubcategory.getHalfday() == 0) {
            viewHolder.edittextHalfdayPrice.setText("");
        } else {
            viewHolder.edittextHalfdayPrice.setText(kamgarSubcategory.getHalfday() + "");
        }

        if (kamgarSubcategory.getFullday() == 0) {
            viewHolder.edittextFulldayPrice.setText("");
        } else {
            viewHolder.edittextFulldayPrice.setText(kamgarSubcategory.getFullday() + "");
        }

        if (kamgarSubcategory.getWeekly() == 0) {
            viewHolder.edittextWeeklyPrice.setText("");
        } else {
            viewHolder.edittextWeeklyPrice.setText(kamgarSubcategory.getWeekly() + "");
        }

        if (kamgarSubcategory.getMonthly() == 0) {
            viewHolder.edittextMonthlyPrice.setText("");
        } else {
            viewHolder.edittextMonthlyPrice.setText(kamgarSubcategory.getMonthly() + "");
        }


       /* viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KamgarListActivity.class);
                context.startActivity(intent);

            }
        });*/


        buttonDoneSubcategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// reyclerView.findViewHolderforAdaperPosition(position)

                /*
                {
"category_id" : 1,
"subcategory" : [
{
"id" : 1,
"hourly" : 100,
"halfday" : 500,
"fullday" : 600,
"weekly" : 0,
"monthly" : 2
},
{
"id" : 3,
"hourly" : 100,
"halfday" : 500,
"fullday" : 600,
"weekly" : 0,
"monthly" : 2000
}
]
}
                 */
                try {
                    List<KamgarPrice> listToSend = new ArrayList<>();

                    JSONObject finalJSONObject = new JSONObject();
                    JSONArray jsonArray = new JSONArray();
                    finalJSONObject.put("category_id", categoryId);

                    boolean callAPI = false;

                    for (int i = 0; i < listOfChecks.size(); i++) {
                        if (listOfChecks.get(i)) {
                            ViewHolder holder = (ViewHolder) recyclerView.findViewHolderForAdapterPosition(i);
                            if (holder == null) {
                                holder = holderHashMap.get(i);
                            }
                          /*  KamgarPrice kamgarPrice = new KamgarPrice();
                            kamgarPrice.setHourly(holder.edittextHourlyPrice.getText().toString() + " SubCId : " + holder.checkboxSubcategoryName.getText());
                            kamgarPrice.setHalfDay(holder.edittextHalfdayPrice.getText().toString());
                            kamgarPrice.setFullDay(holder.edittextFulldayPrice.getText().toString());
                            kamgarPrice.setWeekly(holder.edittextWeeklyPrice.getText().toString());
                            kamgarPrice.setMonthly(holder.edittextMonthlyPrice.getText().toString());
*/
                            String hr = holder.edittextHourlyPrice.getText().toString();

                            String halfDay = holder.edittextHalfdayPrice.getText().toString();
                            String fullDay = holder.edittextFulldayPrice.getText().toString();
                            String weekly = holder.edittextWeeklyPrice.getText().toString();
                            String monthly = holder.edittextMonthlyPrice.getText().toString();


                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("id", list.get(i).getId());

                            jsonObject.put("hourly", hr );
                            jsonObject.put("halfday", halfDay);
                            jsonObject.put("fullday", fullDay);
                            jsonObject.put("weekly", weekly);
                            jsonObject.put("monthly", monthly );
                            jsonArray.put(jsonObject);

                            //listToSend.add(kamgarPrice);

                            callAPI = true;
                        }
                    }
                    finalJSONObject.put("subcategory", jsonArray);
                    Log.e("Data:", finalJSONObject.toString());

                    RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), finalJSONObject.toString());

                    if (callAPI) {
                        if (NetworkUtil.hasConnectivity(context)) {
                            callSubmitAPI(body);
                        } else {
                            Toast.makeText(context, R.string.no_internet_message, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "Please select", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setDataPrefilled(KamgarSubcategoriesResponse.Subcategory subcategory, ViewHolder viewHolder, int position) {

        if (subcategory.getIsActive() == 1) {
            viewHolder.checkboxSubcategoryName.setChecked(true);
            viewHolder.edittextFulldayPrice.setEnabled(true);
            viewHolder.edittextHalfdayPrice.setEnabled(true);
            viewHolder.edittextHourlyPrice.setEnabled(true);
            viewHolder.edittextMonthlyPrice.setEnabled(true);
            viewHolder.edittextWeeklyPrice.setEnabled(true);

            viewHolder.edittextFulldayPrice.setText(subcategory.getFullday() + "");
            viewHolder.edittextHalfdayPrice.setText(subcategory.getHalfday() + "");
            viewHolder.edittextHourlyPrice.setText(subcategory.getHourly() + "");
            viewHolder.edittextMonthlyPrice.setText(subcategory.getMonthly() + "");
            viewHolder.edittextWeeklyPrice.setText(subcategory.getWeekly() + "");

            listOfChecks.set(position, true);

        } else {
            viewHolder.checkboxSubcategoryName.setChecked(false);
            viewHolder.edittextFulldayPrice.setEnabled(false);
            viewHolder.edittextHalfdayPrice.setEnabled(false);
            viewHolder.edittextHourlyPrice.setEnabled(false);
            viewHolder.edittextMonthlyPrice.setEnabled(false);
            viewHolder.edittextWeeklyPrice.setEnabled(false);
        }
    }


    private void callSubmitAPI(RequestBody body) {

        progressDialogForAPI = new ProgressDialog(context);
        progressDialogForAPI.setCancelable(false);
        progressDialogForAPI.setIndeterminate(true);
        progressDialogForAPI.setMessage("Please wait...");
        progressDialogForAPI.show();

        SharedPreferenceManager.setApplicationContext(context);
        String token = SharedPreferenceManager.getKamgarObject().getSuccess().getToken();

        String headers = "Bearer " + token;
        Call<KamgarUpdateStatus> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).updateKamgarStatus(headers, body);
        requestCallback.enqueue(new Callback<KamgarUpdateStatus>() {
            @Override
            public void onResponse(Call<KamgarUpdateStatus> call, Response<KamgarUpdateStatus> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {

                    KamgarUpdateStatus result = response.body();
                    if (result.getSuccess() != null) {
                        Toast.makeText(context, result.getSuccess(), Toast.LENGTH_SHORT).show();
                        context.finish();
                    }
                } else {
                    // Response code is 401
                    Toast.makeText(context, "Server error occured", Toast.LENGTH_SHORT).show();
                }

                if (progressDialogForAPI != null) {
                    progressDialogForAPI.cancel();
                }
            }

            @Override
            public void onFailure(Call<KamgarUpdateStatus> call, Throwable t) {

                if (t != null) {

                    if (progressDialogForAPI != null) {
                        progressDialogForAPI.cancel();
                    }
                    if (t.getMessage() != null)
                        Log.e("error", t.getMessage());
                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox checkboxSubcategoryName;
        private EditText edittextHourlyPrice;
        private EditText edittextHalfdayPrice;
        private EditText edittextFulldayPrice;
        private EditText edittextWeeklyPrice;
        private EditText edittextMonthlyPrice;

        private CardView cardView;


        public ViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardView);
            edittextHourlyPrice = (EditText) itemView.findViewById(R.id.edittextHourlyPrice);
            edittextHalfdayPrice = (EditText) itemView.findViewById(R.id.edittextHalfdayPrice);
            edittextFulldayPrice = (EditText) itemView.findViewById(R.id.edittextFulldayPrice);
            edittextWeeklyPrice = (EditText) itemView.findViewById(R.id.edittextWeeklyPrice);
            edittextMonthlyPrice = (EditText) itemView.findViewById(R.id.edittextMonthlyPrice);
            checkboxSubcategoryName = (CheckBox) itemView.findViewById(R.id.checkboxSubcategoryName);

        }
    }


    @Override
    public void onViewDetachedFromWindow(KamgarSubcategoryAdapter.ViewHolder holder) {
        holderHashMap.put(holder.getAdapterPosition(), holder);
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onViewAttachedToWindow(KamgarSubcategoryAdapter.ViewHolder holder) {
        holderHashMap.remove(holder.getAdapterPosition());
        super.onViewAttachedToWindow(holder);

    }
}
