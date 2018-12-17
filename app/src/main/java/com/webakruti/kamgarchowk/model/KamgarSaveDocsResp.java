package com.webakruti.kamgarchowk.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DELL on 12/17/2018.
 */

public class KamgarSaveDocsResp {

        @SerializedName("success")
        private Success success;

        public Success getSuccess() {
            return success;
        }

        public void setSuccess(Success success) {
            this.success = success;
        }


    public class Kamgardocs {

        @SerializedName("pan_no")
        private String panNo;
        @SerializedName("pan_copy_url")
        private String panCopyUrl;
        @SerializedName("bank_name")
        private String bankName;
        @SerializedName("bank_acc_no")
        private String bankAccNo;
        @SerializedName("bank_passbook_copy_url")
        private String bankPassbookCopyUrl;

        public String getPanNo() {
            return panNo;
        }

        public void setPanNo(String panNo) {
            this.panNo = panNo;
        }

        public String getPanCopyUrl() {
            return panCopyUrl;
        }

        public void setPanCopyUrl(String panCopyUrl) {
            this.panCopyUrl = panCopyUrl;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getBankAccNo() {
            return bankAccNo;
        }

        public void setBankAccNo(String bankAccNo) {
            this.bankAccNo = bankAccNo;
        }

        public String getBankPassbookCopyUrl() {
            return bankPassbookCopyUrl;
        }

        public void setBankPassbookCopyUrl(String bankPassbookCopyUrl) {
            this.bankPassbookCopyUrl = bankPassbookCopyUrl;
        }

    }

    public class Success {

        @SerializedName("msg")

        private String msg;
        @SerializedName("kamgardocs")

        private Kamgardocs kamgardocs;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Kamgardocs getKamgardocs() {
            return kamgardocs;
        }

        public void setKamgardocs(Kamgardocs kamgardocs) {
            this.kamgardocs = kamgardocs;
        }

    }
}
