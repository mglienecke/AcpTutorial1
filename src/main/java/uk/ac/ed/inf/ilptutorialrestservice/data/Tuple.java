package uk.ac.ed.inf.ilptutorialrestservice.data;

public class Tuple {
    private String item1;
    private String item2;

    public Tuple(){

    }

    public Tuple(String item1, String item2){
        setItem1(item1);
        setItem2(item2);
    }

    /**
     * make usage in Console.out easier
     * @return
     */
    @Override
    public String toString() {
        return "item1: " + getItem1() + " -- item2: " + getItem2();
    }

    public String getItem1() {
        return item1;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }
}
