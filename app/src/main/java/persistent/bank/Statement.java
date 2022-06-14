package persistent.bank;

import java.util.List;

public record Statement(long start, long end, List<Operation> operations) {}