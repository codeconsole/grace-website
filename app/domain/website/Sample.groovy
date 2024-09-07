package website

class Sample {  

    String prop1
    Integer prop2
    Date prop3

    static constraints = {
        prop1 size: 5..15
        prop2 max: 100
    }
}
