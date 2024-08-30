package org.ywk.common.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
public class MultiResponse<T> extends BaseResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<T> datas;

    public static <T> MultiResponse<T> of(List<T> datas) {

        MultiResponse<T> multiBaseResponse = new MultiResponse<>();

        multiBaseResponse.setSuccess(true);
        multiBaseResponse.setDatas(datas);

        return multiBaseResponse;

    }


}
