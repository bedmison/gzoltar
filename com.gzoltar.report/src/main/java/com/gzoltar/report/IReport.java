package com.gzoltar.report;

import java.io.File;
import java.io.IOException;
import java.util.List;
import com.gzoltar.core.spectrum.ISpectrum;
import com.gzoltar.fl.IFormula;

public interface IReport {

  /**
   * 
   * @return
   */
  public File getOutputDirectory();

  /**
   * 
   * @return
   */
  public List<IFormula> getFormulas();

  /**
   * 
   * @param spectrum
   */
  public void generateReport(final ISpectrum spectrum) throws IOException;
}
