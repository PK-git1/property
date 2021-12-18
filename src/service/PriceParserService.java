package service;

public class PriceParserService {
    public Double parsePrice(String price) throws Exception {
       if(price.length()==0){
           throw new Exception("Invalid price");
       }
       return Double.parseDouble(price);
    }
}
