package persistent.bank.model;

import java.util.List;

import persistent.bank.model.entity.Operation;

public record Statement(long start, long end, List<Operation> operations) {

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Extrato de " + start + " até " + end + "\n\n");

        for (Operation operation : operations) {
            String formatedOperationLine = String.format("\t%d %s - R$ %s", operation.getDate(), operation.getType(), operation.getValue());
            builder.append(formatedOperationLine);
            builder.append("\n");
        }

        return builder.toString();
    }
}