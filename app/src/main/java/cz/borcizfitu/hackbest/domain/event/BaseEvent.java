package cz.borcizfitu.hackbest.domain.event;

/**
 * Base Event.
 * Created by Jan Stanek[st.honza@gmail.com] on {12.11.16}
 **/
public class BaseEvent<T> {
    public static final String TAG = BaseEvent.class.getName();

    protected T data;

    public BaseEvent() {};

    public BaseEvent(T data) {
        this.data = data;
    }

    public T data() {
        return data;
    }
}
