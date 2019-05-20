package com.example.kop.myexampleproject.ui.menu;

import android.os.Parcel;
import java.util.List;

/**
 * 功    能: //TODO
 * 创 建 人: KOP
 * 创建日期: 2019-04-26 09:37
 */
public class MenuBean {

    public static class GetAllgoodsRsBean {

        public static class ResultBean {

            /**
             * code : 0
             * message : 成功
             */

            private int code;

            private String message;

            public int getCode() {
                return code;
            }

            public String getMessage() {
                return message;
            }

            public void setCode(int code) {
                this.code = code;
            }

            public void setMessage(String message) {
                this.message = message;
            }
        }

        public static class ClassListBean implements android.os.Parcelable {

            public static class GoodsListBean implements android.os.Parcelable {

                /**
                 * picDetails : http://114.255.78.33:18080/coffe/2.0/imgController/getPic?picId=d4ce4763b8fb46b8862ce0eaead909bb
                 * eventId : 2c9aaf32736543fe83ae5425a8444dfb
                 * goodsId : 335d8756c1624d40bc653af78fb1c8ee
                 * isSellOut : Y
                 * remark : null
                 * financeName : null
                 * goodsType : 1
                 * currentSaleYN : Y
                 * picList : http://114.255.78.33:18080/coffe/2.0/imgController/getPic?picId=d4ce4763b8fb46b8862ce0eaead909bb
                 * price : 100
                 * cuteRate : 77
                 * isAdded : 1
                 * goodsPic : http://114.255.78.33:18080/coffe/2.0/imgController/getPic?picId=d4ce4763b8fb46b8862ce0eaead909bb
                 * financeId : null
                 * goodsName : 双萃拿铁蜜
                 */

                private String picDetails;

                private String currentSaleYN;

                private int cuteRate;

                private String eventId;

                private String financeId;

                private String financeName;

                private String goodsId;

                private String goodsName;

                private String goodsPic;

                private int goodsType;

                private String isAdded;

                private String isSellOut;

                private String picList;

                private String price;

                private String remark;

                private String name;

                public String getName() {
                    return name;
                }

                public void setName(final String name) {
                    this.name = name;
                }

                public String getCurrentSaleYN() {
                    return currentSaleYN;
                }

                public int getCuteRate() {
                    return cuteRate;
                }

                public String getEventId() {
                    return eventId;
                }

                public String getFinanceId() {
                    return financeId;
                }

                public String getFinanceName() {
                    return financeName;
                }

                public String getGoodsId() {
                    return goodsId;
                }

                public String getGoodsName() {
                    return goodsName;
                }

                public String getGoodsPic() {
                    return goodsPic;
                }

                public int getGoodsType() {
                    return goodsType;
                }

                public String getIsAdded() {
                    return isAdded;
                }

                public String getIsSellOut() {
                    return isSellOut;
                }

                public String getPicDetails() {
                    return picDetails;
                }

                public String getPicList() {
                    return picList;
                }

                public String getPrice() {
                    return price;
                }

                public String getRemark() {
                    return remark;
                }

                public void setCurrentSaleYN(String currentSaleYN) {
                    this.currentSaleYN = currentSaleYN;
                }

                public void setCuteRate(int cuteRate) {
                    this.cuteRate = cuteRate;
                }

                public void setEventId(String eventId) {
                    this.eventId = eventId;
                }

                public void setFinanceId(String financeId) {
                    this.financeId = financeId;
                }

                public void setFinanceName(String financeName) {
                    this.financeName = financeName;
                }

                public void setGoodsId(String goodsId) {
                    this.goodsId = goodsId;
                }

                public void setGoodsName(String goodsName) {
                    this.goodsName = goodsName;
                }

                public void setGoodsPic(String goodsPic) {
                    this.goodsPic = goodsPic;
                }

                public void setGoodsType(int goodsType) {
                    this.goodsType = goodsType;
                }

                public void setIsAdded(String isAdded) {
                    this.isAdded = isAdded;
                }

                public void setIsSellOut(String isSellOut) {
                    this.isSellOut = isSellOut;
                }

                public void setPicDetails(String picDetails) {
                    this.picDetails = picDetails;
                }

                public void setPicList(String picList) {
                    this.picList = picList;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public GoodsListBean() {
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.picDetails);
                    dest.writeString(this.currentSaleYN);
                    dest.writeInt(this.cuteRate);
                    dest.writeString(this.eventId);
                    dest.writeString(this.financeId);
                    dest.writeString(this.financeName);
                    dest.writeString(this.goodsId);
                    dest.writeString(this.goodsName);
                    dest.writeString(this.goodsPic);
                    dest.writeInt(this.goodsType);
                    dest.writeString(this.isAdded);
                    dest.writeString(this.isSellOut);
                    dest.writeString(this.picList);
                    dest.writeString(this.price);
                    dest.writeString(this.remark);
                    dest.writeString(this.name);
                }

                protected GoodsListBean(Parcel in) {
                    this.picDetails = in.readString();
                    this.currentSaleYN = in.readString();
                    this.cuteRate = in.readInt();
                    this.eventId = in.readString();
                    this.financeId = in.readString();
                    this.financeName = in.readString();
                    this.goodsId = in.readString();
                    this.goodsName = in.readString();
                    this.goodsPic = in.readString();
                    this.goodsType = in.readInt();
                    this.isAdded = in.readString();
                    this.isSellOut = in.readString();
                    this.picList = in.readString();
                    this.price = in.readString();
                    this.remark = in.readString();
                    this.name = in.readString();
                }

                public static final Creator<GoodsListBean> CREATOR = new Creator<GoodsListBean>() {
                    @Override
                    public GoodsListBean createFromParcel(Parcel source) {
                        return new GoodsListBean(source);
                    }

                    @Override
                    public GoodsListBean[] newArray(int size) {
                        return new GoodsListBean[size];
                    }
                };
            }

            /**
             * goodsList : [{"picDetails":"http://114.255.78.33:18080/coffe/2.0/imgController/getPic?picId=d4ce4763b8fb46b8862ce0eaead909bb","eventId":"2c9aaf32736543fe83ae5425a8444dfb","goodsId":"335d8756c1624d40bc653af78fb1c8ee","isSellOut":"Y","remark":null,"financeName":null,"goodsType":1,"currentSaleYN":"Y","picList":"http://114.255.78.33:18080/coffe/2.0/imgController/getPic?picId=d4ce4763b8fb46b8862ce0eaead909bb","price":"100","cuteRate":77,"isAdded":"1","goodsPic":"http://114.255.78.33:18080/coffe/2.0/imgController/getPic?picId=d4ce4763b8fb46b8862ce0eaead909bb","financeId":null,"goodsName":"双萃拿铁蜜"}]
             * name : 周四特权
             * goodsClassId : 2c9aaf32736543fe83ae5425a8444dfb
             */

            private boolean isSelected;

            private String name;

            private String goodsClassId;

            private List<GoodsListBean> goodsList;

            public String getGoodsClassId() {
                return goodsClassId;
            }

            public List<GoodsListBean> getGoodsList() {
                return goodsList;
            }

            public String getName() {
                return name;
            }

            public void setGoodsClassId(String goodsClassId) {
                this.goodsClassId = goodsClassId;
            }

            public void setGoodsList(List<GoodsListBean> goodsList) {
                this.goodsList = goodsList;
            }

            public void setName(String name) {
                this.name = name;
            }

            public boolean isSelected() {
                return isSelected;
            }

            public void setSelected(final boolean selected) {
                isSelected = selected;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
                dest.writeString(this.name);
                dest.writeString(this.goodsClassId);
                dest.writeTypedList(this.goodsList);
            }

            public ClassListBean() {
            }

            protected ClassListBean(Parcel in) {
                this.isSelected = in.readByte() != 0;
                this.name = in.readString();
                this.goodsClassId = in.readString();
                this.goodsList = in.createTypedArrayList(GoodsListBean.CREATOR);
            }

            public static final Creator<ClassListBean> CREATOR = new Creator<ClassListBean>() {
                @Override
                public ClassListBean createFromParcel(Parcel source) {
                    return new ClassListBean(source);
                }

                @Override
                public ClassListBean[] newArray(int size) {
                    return new ClassListBean[size];
                }
            };
        }

        private List<ClassListBean> classList;

        /**
         * result : {"code":0,"message":"成功"}
         * classList : [{"goodsList":[{"picDetails":"http://114.255.78.33:18080/coffe/2.0/imgController/getPic?picId=d4ce4763b8fb46b8862ce0eaead909bb","eventId":"2c9aaf32736543fe83ae5425a8444dfb","goodsId":"335d8756c1624d40bc653af78fb1c8ee","isSellOut":"Y","remark":null,"financeName":null,"goodsType":1,"currentSaleYN":"Y","picList":"http://114.255.78.33:18080/coffe/2.0/imgController/getPic?picId=d4ce4763b8fb46b8862ce0eaead909bb","price":"100","cuteRate":77,"isAdded":"1","goodsPic":"http://114.255.78.33:18080/coffe/2.0/imgController/getPic?picId=d4ce4763b8fb46b8862ce0eaead909bb","financeId":null,"goodsName":"双萃拿铁蜜"}],"name":"周四特权","goodsClassId":"2c9aaf32736543fe83ae5425a8444dfb"}]
         */

        private ResultBean result;

        public List<ClassListBean> getClassList() {
            return classList;
        }

        public ResultBean getResult() {
            return result;
        }

        public void setClassList(List<ClassListBean> classList) {
            this.classList = classList;
        }

        public void setResult(ResultBean result) {
            this.result = result;
        }
    }

    /**
     * GetAllgoodsRs : {"result":{"code":0,"message":"成功"},"classList":[{"goodsList":[{"picDetails":"http://114.255.78.33:18080/coffe/2.0/imgController/getPic?picId=d4ce4763b8fb46b8862ce0eaead909bb","eventId":"2c9aaf32736543fe83ae5425a8444dfb","goodsId":"335d8756c1624d40bc653af78fb1c8ee","isSellOut":"Y","remark":null,"financeName":null,"goodsType":1,"currentSaleYN":"Y","picList":"http://114.255.78.33:18080/coffe/2.0/imgController/getPic?picId=d4ce4763b8fb46b8862ce0eaead909bb","price":"100","cuteRate":77,"isAdded":"1","goodsPic":"http://114.255.78.33:18080/coffe/2.0/imgController/getPic?picId=d4ce4763b8fb46b8862ce0eaead909bb","financeId":null,"goodsName":"双萃拿铁蜜"}],"name":"周四特权","goodsClassId":"2c9aaf32736543fe83ae5425a8444dfb"}]}
     */

    private GetAllgoodsRsBean GetAllgoodsRs;

    public GetAllgoodsRsBean getGetAllgoodsRs() {
        return GetAllgoodsRs;
    }

    public void setGetAllgoodsRs(GetAllgoodsRsBean GetAllgoodsRs) {
        this.GetAllgoodsRs = GetAllgoodsRs;
    }
}
