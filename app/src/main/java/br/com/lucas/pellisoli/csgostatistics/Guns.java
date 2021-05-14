package br.com.lucas.pellisoli.csgostatistics;

public class Guns {

  private int id;
  private String name;
  private String price;
  private String kill_award;
  private String magazine_size;


  public Guns() {
  }

  public Guns(String name, String price, String kill_award, String magazine_size) {
    this.name = name;
    this.price = price;
    this.kill_award = kill_award;
    this.magazine_size = magazine_size;
  }

  public Guns(int id, String name, String price, String kill_award, String magazine_size) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.kill_award = kill_award;
    this.magazine_size = magazine_size;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getKill_award() {
    return kill_award;
  }

  public void setKill_award(String kill_award) {
    this.kill_award = kill_award;
  }

  public String getMagazine_size() {
    return magazine_size;
  }

  public void setMagazine_size(String magazine_size) {
    this.magazine_size = magazine_size;
  }

  @Override
  public String toString() {
    return id + " - " + this.name + " | " + this.price;
  }
}
