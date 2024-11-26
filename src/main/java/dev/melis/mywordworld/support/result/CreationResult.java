package dev.melis.mywordworld.support.result;

public class CreationResult {

    private OperationFailureReason reason;
    private OperationResult result;
    private String message;

    private CreationResult() {}

    public static CreationResult success(){
        return new CreationResult()
                .setResult(OperationResult.SUCCESS);
    }
    public static CreationResult success(String message){
        return new CreationResult()
                .setResult(OperationResult.SUCCESS)
                .setMessage(message);
    }

    public static CreationResult failure(OperationFailureReason reason){
        return  new CreationResult()
                .setResult(OperationResult.FAILURE)
                .setReason(reason);
    }

    public static CreationResult failure(OperationFailureReason reason,String message){
        return  new CreationResult()
                .setResult(OperationResult.FAILURE)
                .setReason(reason)
                .setMessage(message);
    }

    public OperationResult getResult() {
        return result;
    }

    public CreationResult setResult(OperationResult result) {
        this.result = result;
        return this;
    }

    public OperationFailureReason getReason() {
        return reason;
    }

    public CreationResult setReason(OperationFailureReason reason) {
        this.reason = reason;
        return this;
    }
    public String getMessage() {
        return message;
    }

    public CreationResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public boolean isSuccess(){
        return result.equals(OperationResult.SUCCESS);
    }
}
