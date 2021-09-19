package com.example.securedwebapplication.DAO;
//Item1
public class DaoFactory {
    public static DAOI  DataBaseFactory(String Type,String PathF1,String PathF2,int InitialSize){
        switch (Type){
            case "DaoImpl":
                try {
                    return new DaoImpl(PathF1,PathF2,InitialSize);
                } catch (Exception e) {
                    System.out.println(e);
                }break;
        }
        //Item 54
            throw new IllegalArgumentException("Unknown Data Base");
    }
}
