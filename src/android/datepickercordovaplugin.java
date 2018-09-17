package com.kuldeep.datepickercordovaplugin;

import android.app.DatePickerDialog;
import android.widget.DatePicker;


import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

/**
 * Cordova main class , which could receive call from the javascript file.
 * this class is responsible for call to the other java file and source code.
 *
 * this @class datepicker would be calling android platfrom datepicker and will result
 * in jsonobject with date selected by the user.
 * @result {
 *     "date":31/07/1994
 * }
 */


public class datepickercordovaplugin extends CordovaPlugin {
    private Calendar calendar;
    PluginResult result = null;
    private CallbackContext callbackContext = null;
    private int year,month,day;
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        if (action.equals("showDateMethod")) {
           
            if(args.length() != 0){
                //when the default , date is provided by the first json object of the array, and array contain the json object.
                
            year = args.getJSONObject(0).getInt("year");
            month = args.getJSONObject(0).getInt("month");
            day = args.getJSONObject(0).getInt("day");
            DatePickerShow(999,year, month+1, day);
            
            //for now result is no_result for the plugin and call back is true, hence resukt would be below,
            //sent after use input
            PluginResult result = new PluginResult(PluginResult.Status.NO_RESULT);
            result.setKeepCallback(true);
            }
            else{
                //when the default , date is not provided by the first json object of the array, and array is empty.
                
            calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
            this.DatePickerShow(999,year, month+1, day);
            
            //for now result is no_result for the plugin and call back is true, hence resukt would be below,
            //sent after use input    
            PluginResult result = new PluginResult(PluginResult.Status.NO_RESULT);
            result.setKeepCallback(true);
            }
            return true;
        }
        return false;
    }


    /**
     *
     * @param id
     * @param year
     * @param month
     * @param day
     */

   protected void DatePickerShow(int id,int year,int month,int day) {
      
       if (id == 999) {
          //would invoke the default 
          // datepicker if the android platform, with the date provided in 
          //parameter of the function.
          DatePickerDialog datePicker = new DatePickerDialog(this.cordova.getActivity(), new DatePickerDialog.OnDateSetListener() {
              @Override
              public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                  String date = String.valueOf(dayOfMonth) + "/" + String.valueOf(monthOfYear+1)
                          + "/" + String.valueOf(year);
                  setDate(date);
              }
          }, year, month, day);
          datePicker.show();
      }
   }

    /**
     *
     * @param date
     * @result is the Plugin result, on callback result.
     */
   protected  void setDate(String date){
       //Once the date is selected from the user, date would be sent
       //here and the new JSON Object would be created for the result of the plugin
       //would be sent to the user in cordova, where JS function give the result to the user.
       
        JSONObject jsonObject = new JSONObject();
       try {
           jsonObject.put("date",date);
       } catch (JSONException e) {
           e.printStackTrace();
       }
       result = new PluginResult(PluginResult.Status.OK,
               jsonObject);
       result.setKeepCallback(false);
       if (callbackContext != null) {
           callbackContext.sendPluginResult(result);
           
           //no more result , hence the context is cleared.
           callbackContext = null;
       }
   }




}
