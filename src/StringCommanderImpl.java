// -*- Java -*-
/*!
 * @file  StringCommanderImpl.java
 * @brief String Commander GUI Component
 * @date  $Date$
 *
 * $Id$
 */

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import jp.go.aist.rtm.RTC.DataFlowComponentBase;
import jp.go.aist.rtm.RTC.Manager;
import jp.go.aist.rtm.RTC.port.OutPort;
import jp.go.aist.rtm.RTC.util.DataRef;
import RTC.ReturnCode_t;
import RTC.Time;
import RTC.TimedString;

/*!
 * @class StringCommanderImpl
 * @brief String Commander GUI Component
 *
 */
public class StringCommanderImpl extends DataFlowComponentBase {

  /*!
   * @brief constructor
   * @param manager Maneger Object
   */
	public StringCommanderImpl(Manager manager) {  
        super(manager);
        // <rtc-template block="initializer">
        m_command_val = new TimedString(new Time(0,0), "");
        m_command = new DataRef<TimedString>(m_command_val);
        m_commandOut = new OutPort<TimedString>("command", m_command);
        // </rtc-template>

    }

    /**
     *
     * The initialize action (on CREATED->ALIVE transition)
     * formaer rtc_init_entry() 
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
    @Override
    protected ReturnCode_t onInitialize() {
        // Registration: InPort/OutPort/Service
        // <rtc-template block="registration">
        
        // Set OutPort buffer
        addOutPort("command", m_commandOut);
        // </rtc-template>
        

    	inputFrame = new InputFrame();
    	inputFrame.setVisible(true);
    	
        return super.onInitialize();
    }

    /***
     *
     * The finalize action (on ALIVE->END transition)
     * formaer rtc_exiting_entry()
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
    @Override
    protected ReturnCode_t onFinalize() {
    	inputFrame.setVisible(false);
    	inputFrame = null;
        return super.onFinalize();
    }

    /***
     *
     * The startup action when ExecutionContext startup
     * former rtc_starting_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    protected ReturnCode_t onStartup(int ec_id) {
//        return super.onStartup(ec_id);
//    }

    /***
     *
     * The shutdown action when ExecutionContext stop
     * former rtc_stopping_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    protected ReturnCode_t onShutdown(int ec_id) {
//        return super.onShutdown(ec_id);
//    }

    /***
     *
     * The activated action (Active state entry action)
     * former rtc_active_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
    @Override
    protected ReturnCode_t onActivated(int ec_id) {
        return super.onActivated(ec_id);
    }

    /***
     *
     * The deactivated action (Active state exit action)
     * former rtc_active_exit()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
    @Override
    protected ReturnCode_t onDeactivated(int ec_id) {
        return super.onDeactivated(ec_id);
    }

    /***
     *
     * The execution action that is invoked periodically
     * former rtc_active_do()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
    @Override
    protected ReturnCode_t onExecute(int ec_id) {
    	String output = inputFrame.popData();
    	if(output != null) {
    		System.out.println("[RTC.StringCommander] Output String = " + output);
    		this.m_command.v.data = output;
    		m_commandOut.write();
    	}
        return super.onExecute(ec_id);
    }

    /***
     *
     * The aborting action when main logic error occurred.
     * former rtc_aborting_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//  @Override
//  public ReturnCode_t onAborting(int ec_id) {
//      return super.onAborting(ec_id);
//  }

    /***
     *
     * The error action in ERROR state
     * former rtc_error_do()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    public ReturnCode_t onError(int ec_id) {
//        return super.onError(ec_id);
//    }

    /***
     *
     * The reset action that is invoked resetting
     * This is same but different the former rtc_init_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    protected ReturnCode_t onReset(int ec_id) {
//        return super.onReset(ec_id);
//    }

    /***
     *
     * The state update action that is invoked after onExecute() action
     * no corresponding operation exists in OpenRTm-aist-0.2.0
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    protected ReturnCode_t onStateUpdate(int ec_id) {
//        return super.onStateUpdate(ec_id);
//    }

    /***
     *
     * The action that is invoked when execution context's rate is changed
     * no corresponding operation exists in OpenRTm-aist-0.2.0
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    protected ReturnCode_t onRateChanged(int ec_id) {
//        return super.onRateChanged(ec_id);
//    }
//
    // DataInPort declaration
    // <rtc-template block="inport_declare">
    
    // </rtc-template>

    // DataOutPort declaration
    // <rtc-template block="outport_declare">
    protected TimedString m_command_val;
    protected DataRef<TimedString> m_command;
    /*!
     */
    protected OutPort<TimedString> m_commandOut;

    
    // </rtc-template>
    
    public class InputFrame extends JFrame {
    	
    	private JTextField textField;
    	
    	private Object mutex = new Object();
    	private String data;
    	
    	public String popData() {
    		synchronized(mutex) {
	    		String buf = data;
	    		data = null;
	    		return buf;
    		}
    	}
    	
    	public InputFrame() {
    		super("Input Frame");
    		super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    		super.addWindowListener(new WindowAdapter() {
    			
    			@Override
				public void windowClosing(WindowEvent e) {
    				StringCommanderImpl.this.exit();
    				System.exit(0);
				}
    		});
    		super.setLayout(new GridLayout(1,3));
    		super.setSize(new Dimension(300, 60));
    		super.add(new JLabel("Input Command"));
    		textField = new JTextField();
    		super.add(textField);
    		super.add(new JButton(new AbstractAction("Send") {

				@Override
				public void actionPerformed(ActionEvent e) {
					synchronized(mutex) {
						data = textField.getText();
					}
				}
    			
    		}));
    		
    	}
    	
    	
    }
    protected InputFrame inputFrame;

    // CORBA Port declaration
    // <rtc-template block="corbaport_declare">
    
    // </rtc-template>

    // Service declaration
    // <rtc-template block="service_declare">
    
    // </rtc-template>

    // Consumer declaration
    // <rtc-template block="consumer_declare">
    
    // </rtc-template>


}
