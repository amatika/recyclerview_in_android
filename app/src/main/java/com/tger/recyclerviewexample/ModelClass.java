package com.tger.recyclerviewexample;

public class ModelClass {

  private int imageview;
  private String textview1;
  private String textview2;
  private String textview3;
  //new code
  private String divider;
   ModelClass (int imageview ,String textview1,String textview2,String textview3,String divider)
   {
       this.imageview=imageview;
       this.textview1=textview1;
       this.textview2=textview2;
       this.textview3=textview3;
       this.divider=divider;
   }

  public int getImageview() {
        return imageview;
    }
  public String getTextview1() {
        return textview1;
    }
  public String getDivider()
    {
        return divider;
    }
  public String getTextview2() {
        return textview2;
    }
  public String getTextview3() {
        return textview3;
    }

    public void setImageview(int imageview) {
        this.imageview = imageview;
    }

    public void setTextview1(String textview1) {
        this.textview1 = textview1;
    }

    public void setTextview2(String textview2) {
        this.textview2 = textview2;
    }

    public void setTextview3(String textview3) {
        this.textview3 = textview3;
    }

    public void setDivider(String divider) {
        this.divider = divider;
    }
}
