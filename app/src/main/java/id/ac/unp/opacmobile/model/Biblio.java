package id.ac.unp.opacmobile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Biblio {

    @SerializedName("biblio_id")
    @Expose
    public String biblioId;
    @SerializedName("gmd_id")
    @Expose
    public String gmdId;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("sor")
    @Expose
    public Object sor;
    @SerializedName("edition")
    @Expose
    public Object edition;
    @SerializedName("isbn_issn")
    @Expose
    public String isbnIssn;
    @SerializedName("publisher_id")
    @Expose
    public String publisherId;
    @SerializedName("publish_year")
    @Expose
    public String publishYear;
    @SerializedName("collation")
    @Expose
    public String collation;
    @SerializedName("series_title")
    @Expose
    public String seriesTitle;
    @SerializedName("call_number")
    @Expose
    public String callNumber;
    @SerializedName("language_id")
    @Expose
    public String languageId;
    @SerializedName("source")
    @Expose
    public Object source;
    @SerializedName("publish_place_id")
    @Expose
    public String publishPlaceId;
    @SerializedName("classification")
    @Expose
    public String classification;
    @SerializedName("notes")
    @Expose
    public Object notes;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("file_att")
    @Expose
    public Object fileAtt;
    @SerializedName("opac_hide")
    @Expose
    public String opacHide;
    @SerializedName("promoted")
    @Expose
    public String promoted;
    @SerializedName("labels")
    @Expose
    public Object labels;
    @SerializedName("frequency_id")
    @Expose
    public String frequencyId;
    @SerializedName("spec_detail_info")
    @Expose
    public Object specDetailInfo;
    @SerializedName("content_type_id")
    @Expose
    public Object contentTypeId;
    @SerializedName("media_type_id")
    @Expose
    public Object mediaTypeId;
    @SerializedName("carrier_type_id")
    @Expose
    public Object carrierTypeId;
    @SerializedName("input_date")
    @Expose
    public String inputDate;
    @SerializedName("last_update")
    @Expose
    public String lastUpdate;
    @SerializedName("uid")
    @Expose
    public Object uid;

}