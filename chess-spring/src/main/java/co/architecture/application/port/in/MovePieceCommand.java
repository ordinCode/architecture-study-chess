package co.architecture.application.port.in;


public class MovePieceCommand {
    private String source;
    private String target;

    public MovePieceCommand() {
    }

    public MovePieceCommand(String source, String target) {
        this.source = source;
        this.target = target;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }
}
