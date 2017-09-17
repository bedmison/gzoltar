package com.gzoltar.core.instr.matchers;

import java.security.CodeSource;
import java.security.ProtectionDomain;
import javassist.CtClass;
import javassist.CtMethod;

public class SourceLocationMatcher extends AbstractMatcher {

  private final boolean inclNoLocationClasses;

  private final String buildLocation;

  private final ProtectionDomain protectionDomain;

  public SourceLocationMatcher(final boolean inclNoLocationClasses, final String buildLocation,
      final ProtectionDomain protectionDomain) {
    this.inclNoLocationClasses = inclNoLocationClasses;
    this.buildLocation = buildLocation.replace(" ", "%20");
    this.protectionDomain = protectionDomain;
  }

  @Override
  public final boolean matches(final CtClass c) {
    if (!this.inclNoLocationClasses && !this.hasSourceLocation(this.protectionDomain)) {
      return false;
    }
    return true;
  }

  /**
   * Checks whether this protection domain is associated with a source location.
   * 
   * @param protectionDomain protection domain to check (or <code>null</code>)
   * @return <code>true</code> if a source location is defined
   */
  private boolean hasSourceLocation(final ProtectionDomain protectionDomain) {
    if (protectionDomain == null) {
      return false;
    }
    final CodeSource codeSource = protectionDomain.getCodeSource();
    if (codeSource == null) {
      return false;
    }
    if (codeSource.getLocation() == null) {
      return false;
    }
    return codeSource.getLocation().getPath().startsWith(this.buildLocation);
  }

  @Override
  public final boolean matches(final CtMethod m) {
    return false;
  }

}