package everyone.delivery.demo.common.request.dto;

/***
 * > 오름차순 내림 차순을 enum 으로
 */
public enum OrderBy {
    ASC,DESC;

    public boolean isAsc(){
        return this.equals(ASC);
    }
}
