package com.company.domain;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ipodetails")
public class IPODetails implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "companyname", nullable = false)
    private String companyName;

    @Column(name = "stockexchange", nullable = false)
    private String stockExchange;

    @Column(name = "pricepershare", nullable = false)
    private String pricePerShare;

    @Column(name = "totalnumberofshares", nullable = false)
    private String numberOfShares;

    @Column(name = "opendatetime", nullable = false)
    private String openTime;

    @Column(name = "remarks", nullable = false)
    private String remarks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStockExchange() {
        return stockExchange;
    }

    public void setStockExchange(String stockExchange) {
        this.stockExchange = stockExchange;
    }

    public String getPricePerShare() {
        return pricePerShare;
    }

    public void setPricePerShare(String pricePerShare) {
        this.pricePerShare = pricePerShare;
    }

    public String getNumberOfShares() {
        return numberOfShares;
    }

    public void setNumberOfShares(String numberOfShares) {
        this.numberOfShares = numberOfShares;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
