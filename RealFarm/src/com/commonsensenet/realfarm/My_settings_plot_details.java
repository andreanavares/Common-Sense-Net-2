package com.commonsensenet.realfarm;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import com.commonsensenet.realfarm.control.NumberPicker;
import com.commonsensenet.realfarm.dataaccess.RealFarmProvider;
import com.commonsensenet.realfarm.model.DialogData;
import com.commonsensenet.realfarm.ownCamera.OwnCameraActivity;
import com.commonsensenet.realfarm.utils.ApplicationTracker;
import com.commonsensenet.realfarm.utils.SoundQueue;
import com.commonsensenet.realfarm.utils.ApplicationTracker.EventType;
import com.commonsensenet.realfarm.view.DialogAdapter;
import com.commonsensenet.realfarm.view.DialogArrayLists;

public class My_settings_plot_details extends HelpEnabledActivityOld {

	private final Context mContext = this;
	public static final String LOG_TAG = "enter_size";
	public static final String LOG_TAG2 = "add_plot_to_database";

	private RealFarmProvider mDataProvider;
	private String mMainCrop = "0";
	private String mPlotImage = "0";
	private int mSeedTypeId = 0;
	private String mSoilType = "0";
	private String mSize = "0";
	private final My_settings_plot_details parentReference = this;
	private HashMap<String, String> resultsMap;

	private void addPlotToDatabase() {

		Global.plotId = mDataProvider.insertPlot(Global.userId, mSeedTypeId,
				mPlotImage, mSoilType, 0, 0, Integer.parseInt(mSize));

		ApplicationTracker.getInstance().logEvent(EventType.CLICK, LOG_TAG2,"add plot to database");
		
		Toast.makeText(
				getBaseContext(),
				"Plot Details is put to Database " + mPlotImage + " "
						+ mSoilType, Toast.LENGTH_SHORT).show();
	}

	public void onBackPressed() {

		SoundQueue.getInstance().stop();

		Intent adminintent123 = new Intent(My_settings_plot_details.this,
				Homescreen.class);
		startActivity(adminintent123);
		My_settings_plot_details.this.finish();
	}

	public void onCreate(Bundle savedInstanceState) {
		// super.onCreate(savedInstanceState);
		// sets the layout
		// setContentView(R.layout.my_settings_plot_details);
		// gets the database provided
		super.onCreate(savedInstanceState, R.layout.my_settings_plot_details); // 25-06-2012
		setHelpIcon(findViewById(R.id.helpIndicator));

		mDataProvider = RealFarmProvider.getInstance(mContext);
		
		resultsMap = new HashMap<String, String>(); 
		resultsMap.put("mSoilType", "0");
		resultsMap.put("mMainCrop", "0");
		resultsMap.put("mSize", "0");

		ImageButton home1 = (ImageButton) findViewById(R.id.aggr_img_home1);
		ImageButton help1 = (ImageButton) findViewById(R.id.aggr_img_help1);
		help1.setOnLongClickListener(this);

		final View plotImage; // 20-06-2012
		final View soilType;
		final View mainCrop;
		final View size;

		plotImage = (View) findViewById(R.id.plot_tr); // 20-06-2012
		soilType = (View) findViewById(R.id.soiltype_tr);
		mainCrop = (View) findViewById(R.id.maincrop_tr);
		size = (View) findViewById(R.id.size_tr);


		plotImage.setOnLongClickListener(this); // 20-06-2012
		soilType.setOnLongClickListener(this);
		mainCrop.setOnLongClickListener(this);
		size.setOnLongClickListener(this);

		home1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent adminintent123 = new Intent(
						My_settings_plot_details.this, Homescreen.class);
				startActivity(adminintent123);
				My_settings_plot_details.this.finish();

			}
		});

		if (Global.cameraFlag == true) {
			Global.cameraFlag = false;
			final ImageView img_1;
			img_1 = (ImageView) findViewById(R.id.dlg_plot_img_test);
			mPlotImage = Global.plotImagePath;

			img_1.setImageBitmap(Global.rotated);

		}

		View plotimage = (View) findViewById(R.id.dlg_plot_img_test);
		View plotcrop = (View) findViewById(R.id.dlg_lbl_crop_plot);
		View plotsoil = (View) findViewById(R.id.dlg_lbl_soil_plot); 
		View plotsize = (View) findViewById(R.id.size_txt);
		View plotok = (View) findViewById(R.id.button_ok);
		View plotcancel = (View) findViewById(R.id.button_cancel); // 25-06-2012

		((View) findViewById(R.id.dlg_plot_img_test))
				.setOnLongClickListener(parentReference); // Audio integration
		((View) findViewById(R.id.maincrop_tr))
				.setOnLongClickListener(parentReference);
		((View) findViewById(R.id.dlg_lbl_soil_plot))
				.setOnLongClickListener(parentReference);
		((View) findViewById(R.id.button_ok))
				.setOnLongClickListener(parentReference);
		((View) findViewById(R.id.button_cancel)) // 25-06-2012
		.setOnLongClickListener(parentReference);
		((View) findViewById(R.id.size_tr)) 
			.setOnLongClickListener(parentReference);

		// PlotImage =(EditText) findViewById(R.id.plotimage);
		// SoilType = (EditText)findViewById(R.id.soiltype);
		// MainCrop = (EditText)findViewById(R.id.maincrop);
		
		plotsize.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				stopaudio();
				
				displayDialogNP("Enter plot size", "mSize", R.raw.dateinfo, 0, 50, 0, 0.1, 1, R.id.size_txt, R.id.size_tr, R.raw.dateinfo, R.raw.dateinfo, R.raw.dateinfo, R.raw.dateinfo);

			}
		});

		plotimage.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				startActivity(new Intent(My_settings_plot_details.this,
						OwnCameraActivity.class));

				// mPlotImage = "Image";

				My_settings_plot_details.this.finish();

			}
		});

		plotsoil.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Log.d("in plot image dialog", "in dialog");
				stopAudio();
				final ArrayList<DialogData> m_entries = DialogArrayLists.getSoilTypeArray(v);
				displayDialog(v, m_entries, "mSoilType", "Select the soil type", R.raw.problems, R.id.dlg_lbl_soil_plot, R.id.soiltype_tr, 0);

			}
		});

		// add the event listeners
		plotcrop.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Log.d("in crop plot dialog", "in dialog");
				
				stopAudio();
				ArrayList<DialogData> m_entries = mDataProvider.getVarieties();
				displayDialog(v, m_entries, "mMainCrop", "Select the variety", R.raw.problems, R.id.dlg_lbl_crop_plot, R.id.maincrop_tr, 0);
			}
		});

		plotok.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				mSoilType = resultsMap.get("mSoilType");
				mMainCrop = resultsMap.get("mMainCrop");
				mSize = resultsMap.get("mSize");
				
				int flag1, flag2, flag3, flag4;
				if (mPlotImage.toString().equalsIgnoreCase("0")) {
					flag1 = 1;

					View tr_feedback = (View) findViewById(R.id.plot_tr);

					tr_feedback.setBackgroundResource(R.drawable.def_img_not);

				} else {
					flag1 = 0;

					View tr_feedback = (View) findViewById(R.id.plot_tr);

					tr_feedback.setBackgroundResource(android.R.drawable.list_selector_background);
				}

				// TODO: overrides image requirement
				flag1 = 0;

				if (mSoilType.toString().equalsIgnoreCase("0")) {

					flag2 = 1;

					View tr_feedback = (View) findViewById(R.id.soiltype_tr);

					tr_feedback.setBackgroundResource(R.drawable.def_img_not);
				} else {

					flag2 = 0;

					View tr_feedback = (View) findViewById(R.id.soiltype_tr);

					tr_feedback.setBackgroundResource(android.R.drawable.list_selector_background);
				}

				if (mMainCrop.toString().equalsIgnoreCase("0")) {

					flag3 = 1;

					View tr_feedback = (View) findViewById(R.id.maincrop_tr);

					tr_feedback.setBackgroundResource(R.drawable.def_img_not);
				} else {

					flag3 = 0;

					View tr_feedback = (View) findViewById(R.id.maincrop_tr);

					tr_feedback.setBackgroundResource(android.R.drawable.list_selector_background);
				}
				
				if (mSize.toString().equalsIgnoreCase("0")) {

					flag4 = 1;

					View tr_feedback = (View) findViewById(R.id.size_tr);

					tr_feedback.setBackgroundResource(R.drawable.def_img_not);
				} else {

					flag4 = 0;

					View tr_feedback = (View) findViewById(R.id.size_tr);

					tr_feedback.setBackgroundResource(android.R.drawable.list_selector_background);
				}

				if (flag1 == 0 && flag2 == 0 && flag3 == 0 && flag4 == 0) {

					Intent adminintent = new Intent(
							My_settings_plot_details.this, Homescreen.class);

					startActivity(adminintent);
					My_settings_plot_details.this.finish();

					 addPlotToDatabase();
					// mDataProvider.getPlots();

				} else
					initmissingval();

			}
		});

		plotcancel.setOnClickListener(new View.OnClickListener() { // 25-06-2012
					public void onClick(View v) {

						SoundQueue.getInstance().stop();

						Intent adminintent123 = new Intent(
								My_settings_plot_details.this, Homescreen.class);
						startActivity(adminintent123);
						My_settings_plot_details.this.finish();

					}
				});
		/*
		 * PlotImage.setOnClickListener(new View.OnClickListener() { public void
		 * onClick(View v) { System.out.println("in PlotImage"); //
		 * gridview.setAdapter(new ImageAdapter(this)); //
		 * gridview.setAdapter(new ImageAdapter(this)); // Intent adminintent =
		 * new Intent(My_settings_plot_details.this,
		 * VIAggrRecommendation.class); // startActivity(adminintent); // Dialog
		 * dlg = new Dialog(v.getContext());
		 * 
		 * 
		 * Dialog dlg = new Dialog(v.getContext()); //Tested OK
		 * dlg.setContentView(R.layout.plot_image); dlg.setCancelable(true); //
		 * parts TextView dlgDetals = (TextView)
		 * dlg.findViewById(R.id.dlg_lbl_details); ImageView Image1 =
		 * (ImageView) dlg.findViewById(R.id.ImageView01); ImageView Image2 =
		 * (ImageView) dlg.findViewById(R.id.ImageView02); ImageView Image3 =
		 * (ImageView) dlg.findViewById(R.id.ImageView25); ImageView Image4 =
		 * (ImageView) dlg.findViewById(R.id.ImageView27);
		 * 
		 * // ImageView imgAction = (ImageView)
		 * dlg.findViewById(R.id.dlg_img_action); // ImageView imgSeed =
		 * (ImageView) dlg.findViewById(R.id.dlg_img_seed); // dlg.setTitle(
		 * "test"); dlgDetals.setText(" set text" );
		 * 
		 * dlg.show(); }
		 * 
		 * });
		 */

		/*
		 * SoilType.setOnClickListener(new View.OnClickListener() { public void
		 * onClick(View v) { System.out.println("in SoilType");
		 * 
		 * 
		 * Dialog dlg = new Dialog(v.getContext()); //Tested OK
		 * dlg.setContentView(R.layout.soil_type); dlg.setCancelable(true); //
		 * parts TextView dlgDetals = (TextView)
		 * dlg.findViewById(R.id.dlg_lbl_details); ImageView Image1 =
		 * (ImageView) dlg.findViewById(R.id.ImageView01); ImageView Image2 =
		 * (ImageView) dlg.findViewById(R.id.ImageView02); ImageView Image3 =
		 * (ImageView) dlg.findViewById(R.id.ImageView25); ImageView Image4 =
		 * (ImageView) dlg.findViewById(R.id.ImageView27);
		 * 
		 * // ImageView imgAction = (ImageView)
		 * dlg.findViewById(R.id.dlg_img_action); // ImageView imgSeed =
		 * (ImageView) dlg.findViewById(R.id.dlg_img_seed); // dlg.setTitle(
		 * "Soil Types");
		 * 
		 * //imgAction.setImageResource(
		 * mDataProvider.getActionNameById(1).getRes() );
		 * //imgSeed.setImageResource( mDataProvider.getSeedById(1).getRes());
		 * //Log.d(logTag,
		 * "Seed res id: "+String.valueOf(mDataProvider.getSeedById
		 * (1).getRes())); dlg.show(); }
		 * 
		 * });
		 */

	}

	@Override
	public boolean onLongClick(View v) {

		if (v.getId() == R.id.aggr_img_help1) {
			playAudio(R.raw.help);
			ShowHelpIcon(v);
		}

		if (v.getId() == R.id.dlg_plot_img_test) {
			playAudio(R.raw.plotimage);
			ShowHelpIcon(v);
		}

		if (v.getId() == R.id.dlg_lbl_soil_plot) {
			playAudio(R.raw.soiltype);
			ShowHelpIcon(v);
		}

		if (v.getId() == R.id.maincrop_tr) {
			playAudio(R.raw.yieldinfo);
			ShowHelpIcon(v);
		}
		
		if (v.getId() == R.id.size_tr) {
			playAudio(R.raw.yieldinfo);
			ShowHelpIcon(v);
		}

		if (v.getId() == R.id.button_ok) {
			playAudio(R.raw.ok);
			ShowHelpIcon(v);
		}

		if (v.getId() == R.id.button_cancel) {
			playAudio(R.raw.cancel);
			ShowHelpIcon(v);
		}

		if (v.getId() == R.id.plot_tr) {

			playAudio(R.raw.plotimage);
			ShowHelpIcon(v);
		}

		if (v.getId() == R.id.soiltype_tr) {

			playAudio(R.raw.soiltype);
			ShowHelpIcon(v);
		}

		if (v.getId() == R.id.maincrop_tr) {

			playAudio(R.raw.maincrop);
			ShowHelpIcon(v);
		}
		
		if (v.getId() == R.id.size_tr) {

			// playAudio(R.raw.maincrop);
			ShowHelpIcon(v);
		}

		return true;
	}
	
	private void putBackgrounds(DialogData choice, TextView var_text, int imageType){
		if(choice.getBackgroundRes() != -1) var_text.setBackgroundResource(choice.getBackgroundRes());
		if(imageType == 1 || imageType == 2){
			BitmapDrawable bd=(BitmapDrawable) parentReference.getResources().getDrawable(choice.getImageRes());
			int width = bd.getBitmap().getWidth();
			if(width>80) width = 80;
			
			LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		    llp.setMargins(10, 0, 80-width-20, 0); 
		    var_text.setLayoutParams(llp);
			
			var_text.setBackgroundResource(choice.getImageRes());
			if (imageType == 1) var_text.setTextColor(Color.TRANSPARENT);
			else{ 
			    var_text.setGravity(Gravity.TOP); 
			    var_text.setPadding(0, 0, 0, 0); 
			    var_text.setTextSize(20); 
				var_text.setTextColor(Color.BLACK);
			}
		}
	}
	
	private void displayDialog(View v, final ArrayList<DialogData> m_entries, final String mapEntry, final String title, int entryAudio, final int varText, final int trFeedback, final int imageType){ 
		final Dialog dialog = new Dialog(v.getContext());
		dialog.setContentView(R.layout.mc_dialog);
		dialog.setTitle(title);
		dialog.setCancelable(true);
		dialog.setCanceledOnTouchOutside(true);

		DialogAdapter m_adapter = new DialogAdapter(v.getContext(), R.layout.mc_dialog_row, m_entries);
		ListView mList = (ListView)dialog.findViewById(R.id.liste);
		mList.setAdapter(m_adapter);

		dialog.show();
		playAudio(entryAudio); // TODO: onOpen

		mList.setOnItemClickListener(new OnItemClickListener(){ // TODO: adapt the audio in the db
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// Does whatever is specific to the application
				Log.d("var "+position+" picked ", "in dialog");
				TextView var_text = (TextView) findViewById(varText);
				DialogData choice = m_entries.get(position);
				var_text.setText(choice.getName());
				resultsMap.put(mapEntry, choice.getValue());  
				View tr_feedback = (View) findViewById(trFeedback);
				tr_feedback.setBackgroundResource(android.R.drawable.list_selector_background);

				// put backgrounds (specific to the application) TODO: optimize the resize
				putBackgrounds(choice, var_text, imageType);

				// tracks the application usage.
				ApplicationTracker.getInstance().logEvent(
						EventType.CLICK, LOG_TAG, title,
						choice.getValue());
				
				Toast.makeText(parentReference, resultsMap.get(mapEntry), Toast.LENGTH_SHORT).show();
						
				// onClose
				dialog.cancel();
				int iden = choice.getAudioRes();
				//view.getContext().getResources().getIdentifier("com.commonsensenet.realfarm:raw/" + choice.getAudio(), null, null);
				playAudio(iden);
			}});

		mList.setOnItemLongClickListener(new OnItemLongClickListener(){

			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) { // TODO: adapt the audio in the db
				int iden = m_entries.get(position).getAudioRes();
				//view.getContext().getResources().getIdentifier("com.commonsensenet.realfarm:raw/" + m_entries.get(position).getAudio(), null, null);
				playAudioalways(iden);
				return true;
			}});
	}
	
	private void displayDialogNP(String title, final String mapEntry, int openAudio, double min, double max, double init, double inc, int nbDigits, int textField, int tableRow, final int okAudio, final int cancelAudio, final int infoOkAudio, final int infoCancelAudio){ 

		final Dialog dialog = new Dialog(parentReference);
		dialog.setTitle(title);
		dialog.setCancelable(true);
		dialog.setCanceledOnTouchOutside(true);
		playAudio(openAudio); // opening audio
		
		if(!resultsMap.get(mapEntry).equals("0")) init = Double.valueOf(resultsMap.get(mapEntry));
		
		NumberPicker np = new NumberPicker(parentReference, min, max, init, inc, nbDigits);
		dialog.setContentView(np);
		
		final TextView tw_sow = (TextView) findViewById(textField);
		final View tr_feedback = (View) findViewById(tableRow);

		final TextView tw = (TextView)dialog.findViewById(R.id.tw);
		ImageButton ok = (ImageButton)dialog.findViewById(R.id.ok);
		ImageButton cancel = (ImageButton)dialog.findViewById(R.id.cancel);
        ok.setOnClickListener(new View.OnClickListener(){ 
			public void onClick(View view) {
				String result = tw.getText().toString(); 
				resultsMap.put(mapEntry, result); 
				tw_sow.setText(result);
				tr_feedback.setBackgroundResource(android.R.drawable.list_selector_background);
				Toast.makeText(parentReference , result, Toast.LENGTH_LONG).show();
				dialog.cancel();
				playAudio(okAudio); // ok audio
		}});
        cancel.setOnClickListener(new View.OnClickListener(){ 
			public void onClick(View view) {
				dialog.cancel();
				playAudio(cancelAudio); // cancel audio
				ApplicationTracker.getInstance().logEvent(EventType.CLICK, LOG_TAG, "amount", "cancel");
		}});
        ok.setOnLongClickListener(new View.OnLongClickListener(){ 
			public boolean onLongClick(View view) {
				playAudio(infoOkAudio); // info audio
				return true;
		}});
        cancel.setOnLongClickListener(new View.OnLongClickListener(){ 
			public boolean onLongClick(View view) {
				playAudio(infoCancelAudio); // info audio
				return true;
		}});
        tw.setOnLongClickListener(new View.OnLongClickListener(){ 
			public boolean onLongClick(View view) {
				String num = tw.getText().toString();
				playAudio(R.raw.dateinfo); // info audio
				return false;
		}});
        				
		dialog.show();
	}
}
