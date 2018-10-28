package com.example.hesi100.barber;

import android.net.ConnectivityManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * Created by hesi100 on 4/4/2018.
 */

public class Internet {

    private String mainAddress = "http://www.seen-web.com/ghjfksfgdgetegduyfysuy/barber/core/";

    public static boolean isInternetOn() {
        // get Connectivity Manager object to check connection
        ConnectivityManager connec =  (ConnectivityManager) app.context.getSystemService(CONNECTIVITY_SERVICE);
        // Check for network connections
        try {
            if (connec.getNetworkInfo(0).getState() != null)
            {
                if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                        connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {
                    return true;
                }
                else if (
                        connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {
                    return false;
                }
            }
            else
            {
                if (connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING) {
                    return true;
                }
                else if (connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {
                    return false;
                }
            }
        }
        catch (Exception e) {
            if (connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ||
                    connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING) {
                return true;
            }
            else if (connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {
                return false;
            }
        }
        return false;
    }
//    public static String connectToServerByJson(String address, String requestMethod, String JsonDATA) {
//        if(isInternetOn()) {
//            String JsonResponse;
//
//            HttpURLConnection urlConnection = null;
//            BufferedReader reader = null;
//            try {
//                URL url = new URL(address);
//                urlConnection = (HttpURLConnection) url.openConnection();
//               // urlConnection.setDoOutput(true);
//                // is output buffer writter
//                urlConnection.setRequestMethod(requestMethod);
//               urlConnection.setDoOutput(true);
//               urlConnection.setRequestProperty("Content-Type", "application/json");
//               urlConnection.setRequestProperty("Accept", "application/json");
////set headers and method
//                Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
//                writer.write(JsonDATA);
//                writer.flush();
//// json data
//
//                InputStream inputStream = urlConnection.getInputStream();
////input stream
//                StringBuffer buffer = new StringBuffer();
//                writer.close();
//                if (inputStream == null) {
//                    // Nothing to do.
//                    return "fail1";
//                }
//                reader = new BufferedReader(new InputStreamReader(inputStream));
//
//                String inputLine;
//                while ((inputLine = reader.readLine()) != null)
//                    buffer.append(inputLine);
//                if (buffer.length() == 0) {
//                    // Stream was empty. No point in parsing.
//                    return "fail2";
//                }
//                JsonResponse = buffer.toString();
//
////response data
//                Log.i(TAG, JsonResponse);
//                //send to post execute
//                return JsonResponse;
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                if (urlConnection != null) {
//                    urlConnection.disconnect();
//                }
//                if (reader != null) {
//                    try {
//                        reader.close();
//                    } catch (final IOException e) {
//                        Log.e(TAG, "Error closing stream", e);
//                    }
//                }
//            }
//            return "fail3";
//        }
//        else
//        {
//            Toast.makeText(app.context, "اینترنت خود را باز کنید", Toast.LENGTH_LONG).show();
//            return "fail";
//        }
//    }



    private String connectToServer(String address, String RequestMethod) {
        try {
            URL url = new URL(address);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(RequestMethod);
            return inputStreamToString(urlConnection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String connectToServerByJson(String address, String requestMethod, String JsonDATA) {

        String JsonResponse = null;

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(address);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            // is output buffer writter
            urlConnection.setRequestMethod(requestMethod);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
//set headers and method
            Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
            writer.write(JsonDATA);
// json data
            writer.close();
            InputStream inputStream = urlConnection.getInputStream();
//input stream
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return "input";
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String inputLine;
            while ((inputLine = reader.readLine()) != null)
                buffer.append(inputLine);
            if (buffer.length() == 0) {
                // Stream was empty. No point in parsing.
                return "buffer";
            }
            JsonResponse = buffer.toString();
//response data
            Log.i(TAG, JsonResponse);
            //send to post execute
            return JsonResponse;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(TAG, "Error closing stream", e);
                }
            }
        }
        return null;

    }

    private String inputStreamToString(InputStream inputStream) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String nextLine;
        try {
            while ((nextLine = reader.readLine()) != null) {
                stringBuilder.append(nextLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public String userLogin(boolean isInternetAvailable, String user) {

        if (isInternetAvailable) {

            String response = connectToServerByJson(mainAddress + "log_in_customer.php", "POST" , user);
            Log.i("LOG", response + "");

            if (response != null) {
                return response;
            }
            return null;

        } else
            return null;
    }
    public String userRegister(boolean isInternetAvailable, User user) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("gender","male");
            jsonObject.put("name",user.name);
            jsonObject.put("user",user.username);
            jsonObject.put("pass",user.password);
            jsonObject.put("ostan",user.ostan);
            jsonObject.put("city",user.city);
            jsonObject.put("tel",user.tel);
            jsonObject.put("mail",user.email);
            jsonObject.put("sex",1);



        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (isInternetAvailable) {

            String response = connectToServerByJson(mainAddress + "signup.php", "POST" , jsonObject.toString());

            Log.i("LOG", response + "");

            if (response != null) {
                return response;
            }
            return null;

        } else
            return null;
    }
    public WorkTime getBarberTimes(boolean isInternetAvailable, int id_barber) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("id_barber",id_barber);
            jsonObject.put("gender","male");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (isInternetAvailable) {

            String response = connectToServerByJson(mainAddress + "worktimes.php", "POST" , jsonObject.toString());

            Log.i("LOG", response + "");

            if (response != null) {
                JSONArray jsonArray;
                JSONObject res = null;
                try {
                    jsonArray=new JSONArray(response);
                    res=jsonArray.getJSONObject(0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                WorkTime workTime=new WorkTime();
                    try {
                        workTime.getFromjson(res);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                return workTime;
            }
            return null;

        } else
            return null;
    }
    public Barber getBarberService(boolean isInternetAvailable, Barber barber) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("id_barber",barber.id);
            jsonObject.put("gender","male");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (isInternetAvailable) {

            String response = connectToServerByJson(mainAddress + "services.php", "POST" , jsonObject.toString());

            Log.i("LOG", response + "");
            JSONArray jsonArray;
            int i;
            if (response != null) {
                try {
                    jsonArray=new JSONArray(response);
                    for(i=0;i<jsonArray.length();i++)
                    {
                        JSONObject service=jsonArray.getJSONObject(i);
                        Emkanat emkanat=new Emkanat(service);
                        if(service.getString("label").equals("hair"))
                        {
                            barber.emkanatmo.add(emkanat);
                        }
                        else if(service.getString("label").equals("rish"))
                        {
                            barber.emkanatrish.add(emkanat);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return barber;
            }
            return null;
        } else
            return null;
    }
    public String insertReservation(boolean isInternetAvailable, Reservation reservation) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("id_barber",reservation.idbarber);
            jsonObject.put("gender","male");
            jsonObject.put("id_customer",reservation.idcustomer);
            jsonObject.put("name_service",reservation.getJsonService().toString());
            jsonObject.put("cost_service",reservation.cost);
            jsonObject.put("status",1);
            jsonObject.put("off",reservation.off);
            jsonObject.put("got_cost_service",reservation.got_cost_service);
            jsonObject.put("date",reservation.date);
            jsonObject.put("time",reservation.time);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (isInternetAvailable) {

            String response = connectToServerByJson(mainAddress + "reservation.php", "POST" , jsonObject.toString());
            Log.i("LOG", response + "");
            if (response != null) {
                return response;
            }
            return null;
        } else
            return null;
    }
    public String insertReservation_puan_comment(boolean isInternetAvailable, Reservation reservation) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("id_barber",reservation.idbarber);
            jsonObject.put("gender","male");
            jsonObject.put("comment_customer",reservation.comment);
            jsonObject.put("id",reservation.idreserve);
            jsonObject.put("id_customer",reservation.idcustomer);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (isInternetAvailable) {

            String response = connectToServerByJson(mainAddress + "reservation_customer_puan_comment.php", "POST" , jsonObject.toString());
            Log.i("LOG", response + "");
            if (response != null) {
                return response;
            }
            return null;
        } else
            return null;
    }
    public String cancelReservation(boolean isInternetAvailable, int idReserve,int idcustomer) {
        JSONObject jsonObject=new JSONObject();
        try {

            jsonObject.put("gender","male");
            jsonObject.put("id",idReserve);
            jsonObject.put("id_customer",idcustomer);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (isInternetAvailable) {

            String response = connectToServerByJson(mainAddress + "reservation_customer_puan_comment.php", "POST" , jsonObject.toString());
            Log.i("LOG", response + "");
            if (response != null) {
                return response;
            }
            return null;
        } else
            return null;
    }
    public List<Reservation> getReservation(boolean isInternetAvailable,int idcustomer) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("gender","male");
            jsonObject.put("id_customer",idcustomer);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        List<Reservation> list=new ArrayList<>();
        if (isInternetAvailable) {
            String response = connectToServerByJson(mainAddress + "get_customer_reserve.php", "POST" , jsonObject.toString());
            Log.i("LOG", response + "");
            if (response != null) {
                try {
                    JSONArray array=new JSONArray(response);
                    for (int i=0;i<array.length();i++)
                    {
                        JSONObject l=array.getJSONObject(i);
                        Reservation r=new Reservation(l.getInt("id"),l.getInt("id_barber"),l.getString("status"),l.getString("shop_name"),l.getString("city"),l.getString("mahale"));
                        //Reservation(int id,int idbarber,String status,String shop_name,String city,String mahale)
                        list.add(r);
                    }
                    return list;
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            return null;
        } else
            return null;
    }
    public List<Reservation> getListCustomerComment(boolean isInternetAvailable,int idcustomer) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("gender","male");
            jsonObject.put("id_customer",idcustomer);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        List<Reservation> list=new ArrayList<>();
        if (isInternetAvailable) {
            String response = connectToServerByJson(mainAddress + "get_customer_comments.php", "POST" , jsonObject.toString());
            Log.i("LOG", response + "");
            if (response != null) {
                try {
                    JSONArray array=new JSONArray(response);
                    for (int i=0;i<array.length();i++)
                    {
                        JSONObject l=array.getJSONObject(i);
                        Reservation r=new Reservation(l.getInt("id"),l.getString("date"),l.getString("shop_name"),l.getString("time"),l.getString("city"),l.getString("mahale"),l.getString("comment_customer"));
                        //'id' => $fetced_data['id'], 'date' => $fetced_data['date'], 'shop_name'=> $fetced_data['shop_name'], 'time'=>$fetced_data['time'], 'city'=>$fetced_data['city'], 'mahale'=>$fetced_data['mahale'], 'comment_customer'=>$fetced_data['comment_customer']
                        list.add(r);
                    }
                    return list;
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            return null;
        } else
            return null;
    }
    public Barber getBarber(boolean isInternetAvailable, int idbarber) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("id_barber",idbarber);
            jsonObject.put("gender","male");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (isInternetAvailable) {

            String response = connectToServerByJson(mainAddress + "get_barber_for_reserve.php", "POST" , jsonObject.toString());
            Log.i("LOG", response + "");
            JSONObject barberj = null;
            Barber barber=null;
            if (response != null) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    barberj=jsonArray.getJSONObject(0);
                } catch (JSONException e) {
                    e.printStackTrace();
                    barber=new Barber(barberj);
                }
                return barber;
            }
            return null;
        } else
            return null;
    }
    public List<Barber> getListBarberOfMahaleAndFavorite(boolean isInternetAvailable, int idcustomer,String mahale,String city) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("id_customer",idcustomer);
            jsonObject.put("mahale",mahale);
            jsonObject.put("city",city);
            jsonObject.put("gender","male");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (isInternetAvailable) {

            String response = connectToServerByJson(mainAddress + "findby_mahale_andfavorite.php", "POST" , jsonObject.toString());
            Log.i("LOG", response + "");
            List<Barber> list=new ArrayList<>();
            JSONObject barberj;
            if (response != null) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        barberj=jsonArray.getJSONObject(i);
                        Barber barber=new Barber(barberj);
                        list.add(barber);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return list;
            }
            return null;
        } else
            return null;
    }
    public List<Barber> getListBarberOfShopNameAndFavorite(boolean isInternetAvailable, int idcustomer,String shopname,String city) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("id_customer",idcustomer);
            jsonObject.put("shop_name",shopname);
            jsonObject.put("city",city);
            jsonObject.put("gender","male");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (isInternetAvailable) {

            String response = connectToServerByJson(mainAddress + "findby_shopname_andfavorite.php", "POST" , jsonObject.toString());
            Log.i("LOG", response + "");
            List<Barber> list=new ArrayList<>();
            JSONObject barberj;
            if (response != null) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        barberj=jsonArray.getJSONObject(i);
                        Barber barber=new Barber(barberj);
                        list.add(barber);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return list;
            }
            return null;
        } else
            return null;
    }
    public List<Barber> getListBarberByCity(boolean isInternetAvailable,String city) {
        JSONObject jsonObject=new JSONObject();
        try {

            jsonObject.put("city",city);
            jsonObject.put("gender","male");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (isInternetAvailable) {

            String response = connectToServerByJson(mainAddress + "findby_shopname_andfavorite.php", "POST" , jsonObject.toString());
            Log.i("LOG", response + "");
            List<Barber> list=new ArrayList<>();
            JSONObject barberj;
            if (response != null) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        barberj=jsonArray.getJSONObject(i);
                        Barber barber=new Barber(barberj);
                        list.add(barber);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return list;
            }
            return null;
        } else
            return null;
    }
    public List<Comment> getListBarberComment(boolean isInternetAvailable, int idbarber) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("id_barber",idbarber);
            jsonObject.put("gender","male");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (isInternetAvailable) {

            String response = connectToServerByJson(mainAddress + "get_barber_comments.php", "POST" , jsonObject.toString());
            Log.i("LOG", response + "");
            List<Comment> list=new ArrayList<>();
            JSONObject commentj;
            if (response != null) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        commentj=jsonArray.getJSONObject(i);
                        Comment c=new Comment(commentj.getString("username"),commentj.getString("comment_customer"),commentj.getString("date"));
                        //(String username,String nazar,String date)
                        list.add(c);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return list;
            }
            return null;
        } else
            return null;
    }

}
