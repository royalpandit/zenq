package com.abhishek.zeiqindia.AdapterFleet_Owner;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.abhishek.zeiqindia.Bean.PartyBean;
import com.abhishek.zeiqindia.R;

import java.util.List;

public class AdapterGetParty extends RecyclerView.Adapter<AdapterGetParty.ViewHolder> {
    EditText edit_company_name, edit_company_email, edit_company_person, edit_company_number, edit_company_gst, edit_company_pan, edit_company_address;

    Context context;
    String Company_Name;
    String StrRole, StrImagerole;
    String StrUser;
    String StrEmail;
    String StrKyc;
    List<PartyBean> partyBeanList;


    public AdapterGetParty(Context context, List<PartyBean> partyBeanList) {
        this.context = context;
        this.partyBeanList = partyBeanList;
    }


    @Override
    public AdapterGetParty.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_fleet_owner_myparty, viewGroup, false);
        return new AdapterGetParty.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(AdapterGetParty.ViewHolder holder, final int position) {
        Company_Name = partyBeanList.get(position).getCompany_name();
        holder.tv_party_create_date.setText(partyBeanList.get(position).getCreate_date());
        holder.tvf_party_company.setText("Company : " + partyBeanList.get(position).getCompany_name());
        holder.tvf_party_conatct.setText("Person : " + partyBeanList.get(position).getContact_person());
        holder.tvf_party_email.setText("Email : " + partyBeanList.get(position).getEmail());
        holder.btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BtnViewClick(position);

            }
        });

    }



    @Override
    public int getItemCount() {
        //  Log.e("size", "");
        return partyBeanList.size();


    }

    public void BtnViewClick(int position) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_fleet_addmy_part_view);
        Button ok = dialog.findViewById(R.id.btn_add_party);

        edit_company_name = dialog.findViewById(R.id.edit_company_name);
         edit_company_email=dialog.findViewById(R.id.edit_company_email);
        edit_company_person=dialog.findViewById(R.id.edit_company_person);
        edit_company_number=dialog.findViewById(R.id.edit_company_number);
        edit_company_gst=dialog.findViewById(R.id.edit_company_gst);
        edit_company_pan=dialog.findViewById(R.id.edit_company_pan);
        edit_company_address=dialog.findViewById(R.id.edit_company_address);


        edit_company_name.setText(partyBeanList.get(position).getCompany_name());
        edit_company_email.setText(partyBeanList.get(position).getEmail());
        edit_company_person.setText(partyBeanList.get(position).getContact_person());
        edit_company_number.setText(partyBeanList.get(position).getContact_number());
        edit_company_gst.setText(partyBeanList.get(position).getGst());
        edit_company_pan.setText(partyBeanList.get(position).getPan());
        edit_company_address.setText(partyBeanList.get(position).getCompany_address());
ok.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        dialog.dismiss();
    }
});
        dialog.show();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_party_create_date, tvf_party_company, tvf_party_conatct, tvf_own_truck_permit, tvf_party_email;
        Button btn_view;


        public ViewHolder(final View itemView) {
            super(itemView);
            tv_party_create_date = itemView.findViewById(R.id.tv_party_create_date);
            tvf_party_company = itemView.findViewById(R.id.tvf_party_company);
            tvf_party_conatct = itemView.findViewById(R.id.tvf_party_conatct);
            tvf_party_email = itemView.findViewById(R.id.tvf_party_email);
            btn_view = itemView.findViewById(R.id.btn_view);


        }
    }
}
