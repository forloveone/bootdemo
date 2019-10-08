package com.test.design_pattern;

/**
 * 命令模式
 */
public class CommandTest {
    public static void main(String[] args) {
        //调用者
        Invoker in = new Invoker();
//        //接受者
//        Receiver receiver = new Receiver();

        Command command = new CommandImpl();
        in.setCommand(command);
        in.action();
    }
}

/**
 * 调用者
 */
class Invoker {
    private Command command;

    //接受命令
    public void setCommand(Command command) {
        this.command = command;
    }

    //执行命令
    public void action() {
        //参数实体封装
        Object o = new Object();
        command.execute(o);
    }
}

abstract class Command {
    protected final Receiver receiver;

    public Command(Receiver receiver) {
        this.receiver = receiver;
    }

    abstract void execute(Object object);
}

class CommandImpl extends Command {
    //默认的接受者
    public CommandImpl() {
        super(new Receiver());
    }
    public CommandImpl(Receiver receiver) {
        super(receiver);
    }

    /**
     * 实现一个命令
     */
    @Override
    public void execute(Object object) {
        receiver.doSomething(object);
    }
}

//这里应该是个接口,和其实现类,为了简单所以使用了class
class Receiver {
    public void doSomething(Object object) {
        System.out.println("执行");
    }
}