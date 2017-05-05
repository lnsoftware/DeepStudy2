package rx;

import rx.functions.Func0;

public class SomeType {
    private String value;

    public static void main(String[] args) {

        SomeType instance = new SomeType();
        Observable<String> value = instance.valueObservable();
        instance.setValue("Some Value");
        value.subscribe(System.out::println);
    }


    public void setValue(String value) {
        this.value = value;
    }

    public Observable<String> valueObservable() {
        return Observable.just(value);
    }

}