package yzu.money.horoscopecalculator;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.Toast;

import static android.R.attr.button;
import static yzu.money.horoscopecalculator.R.styleable.View;

public class MainActivity extends AppCompatActivity {
    private DatePicker datePicker;
    private NumberPicker monthPickr, datePickr;
    private WebView wv;
    private ProgressDialog pd;
    private Button btn;
    String result="2";

    @Override
    public void onBackPressed() {
        if(wv.canGoBack()) wv.goBack();
        else
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //find views
        wv = (WebView) findViewById(R.id.webView);
        pd = new ProgressDialog(this);
        btn = (Button) findViewById(R.id.button);
        monthPickr = (NumberPicker) findViewById(R.id.numberPicker8);
        datePickr= (NumberPicker) findViewById(R.id.numberPicker9);
        //settings
        btn.setText(R.string.confirmBtn);
        monthPickr.setMaxValue(12);
        monthPickr.setMinValue(1);
        monthPickr.setValue(1);
        datePickr.setMaxValue(31);
        datePickr.setMinValue(1);
        datePickr.setValue(1);

        //set btn onclick listener
        btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                int m = monthPickr.getValue();
                int d = datePickr.getValue();


                //determine horoscope
                if(((m==1||m==3||m==5||m==7||m==8||m==10||m==12)&&d>=1&&d<=31)||((m==4||m==6||m==9||m==11)&&d>=1&&d<=30)||(m==2&&d>=1&&d<=29)){

                    switch (m){
                        case 1:
                            if(d<=20) result="10";
                            else result="11";
                            break;
                        case 2:
                            if(d<=19) result="11";
                            else result="12";
                            break;
                        case 3:
                            if(d<=20) result="12";
                            else result="1";
                            break;
                        case 4:
                            if(d<=20) result="1";
                            else result="2";
                            break;
                        case 5:
                            if(d<=21) result="2";
                            else result="3";
                            break;
                        case 6:
                            if(d<=21) result="3";
                            else result="4";
                            break;
                        case 7:
                            if(d<=23) result="4";
                            else result="5";
                            break;
                        case 8:
                            if(d<=23) result="5";
                            else result="6";
                            break;
                        case 9:
                            if(d<=23) result="6";
                            else result="7";
                            break;
                        case 10:
                            if(d<=23) result="7";
                            else result="8";
                            break;
                        case 11:
                            if(d<=23) result="8";
                            else result="9";
                            break;
                        case 12:
                            if(d<=22) result="9";
                            else result="10";
                            break;
                        default:
                            result="Error";
                            break;

                    }


                }

                wv.setWebViewClient(new mywebview());
                pd.setMessage("Loading...");
                pd.show();
                //enable javascript
                wv.getSettings().setJavaScriptEnabled(true);
                wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

                //wv.loadUrl("http://www.yzu.edu.tw");
                //wv.loadUrl("http://astro.click108.com.tw/daily_8.php?iAstro=8&iAcDay=2016-10-09");
                //wv.loadUrl("http://astro.click108.com.tw/unit002/2010/thought_reading_option_new.php?tid=20&aid=8&sex=2&to=step3&pSex=2#step3");
                wv.loadUrl("http://m.click108.com.tw/astro/index.php?astroNum="+result);
            }
        });






    }


    private class mywebview extends WebViewClient{
        public mywebview(){

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if(pd.isShowing()) pd.dismiss();
            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            Toast.makeText(view.getContext(),"Loading failed.", Toast.LENGTH_LONG).show();
            super.onReceivedError(view, request, error);
        }
    }
}
