package org.ysy.demoj17.commander;

import org.ysy.demoj17.CmdPublisher;

public enum Commander {
    IDREADER(IdReaderCmd.class),
    BTN(ManualConfirmBtnCmd.class),
    PRE(PrepareTruckAndDriverCmd.class),
    W(WeighingCmd.class),
    ;

    public final Class<? extends CmdPublisher> clz;

    Commander(Class<? extends CmdPublisher> clz) {
        this.clz = clz;
    }
}
