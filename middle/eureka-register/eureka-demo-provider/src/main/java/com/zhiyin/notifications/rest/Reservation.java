package com.zhiyin.notifications.rest;

import com.google.common.base.MoreObjects;



/**
 * Created by Jason on 5/3/16.
 */

public class Reservation {

  private Long id;

  private String reservationName;

  public Reservation() {
  }

  public Reservation(String reservationName) {
    this.reservationName = reservationName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getReservationName() {
    return reservationName;
  }

  public void setReservationName(String reservationName) {
    this.reservationName = reservationName;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
            .omitNullValues()
            .add("id", id)
            .add("reservationName", reservationName)
            .toString();
  }
}