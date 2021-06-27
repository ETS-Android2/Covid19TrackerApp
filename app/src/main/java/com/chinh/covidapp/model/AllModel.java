package com.chinh.covidapp.model;



public class AllModel {

    private Latest latest;


    private ConfirmModel deaths;


    private ConfirmModel confirmed;


    private ConfirmModel recovered;

    public Latest getLatest() {
        return latest;
    }

    public void setLatest(Latest latest) {
        this.latest = latest;
    }

    public class Latest {


        private Integer confirmed;

        private Integer deaths;

        private Integer recovered;

        public Integer getConfirmed() {
            return confirmed;
        }

        public void setConfirmed(Integer confirmed) {
            this.confirmed = confirmed;
        }

        public Integer getDeaths() {
            return deaths;
        }

        public void setDeaths(Integer deaths) {
            this.deaths = deaths;
        }

        public Integer getRecovered() {
            return recovered;
        }

        public void setRecovered(Integer recovered) {
            this.recovered = recovered;
        }
    }

    public ConfirmModel getConfirmed() {
        return confirmed;
    }

    public ConfirmModel getDeaths() {
        return deaths;
    }

    public ConfirmModel getRecovered() {
        return recovered;
    }
}