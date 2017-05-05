package littlerx;

/**
 * Created by iceGeneral on 2016/10/15.
 */
public interface Operator<R, T> extends Func1<Subscriber<? super R>, Subscriber<? super T>> {
}
