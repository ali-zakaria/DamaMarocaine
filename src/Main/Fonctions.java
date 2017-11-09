package Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Fonctions {

	static Boolean Hjoue;
	static Boolean UnJoueur;
	static Boolean Case = false;
	static int numCaseH = 1;
	static int numCaseB = 0;
	static int PasBouger = -2;
	static int Bouger = -1;
	static int BougerEtPrendre = -3;
	static int PasDame = 1000;
	static int Cote = 456;
	static int pion = Cote / 8;

	static void CaseSelect(int X, int Y) {

		X = (X / pion) * pion;
		Y = (Y / pion) * pion;

		if (Hjoue == true) {
			if (Case == false) {
				numCaseH = RecherchePionH(X, Y);

				AllumeCase(numCaseH, X, Y);
				Case = true;

			} else {
				EteindreCase(numCaseH, EquipeH.equH.get(numCaseH).getX(),
						EquipeH.equH.get(numCaseH).getY());
				Case = false;
			}
		}

		else {
			if (Case == false) {
				numCaseB = RecherchePionB(X, Y);
				AllumeCase(numCaseB, X, Y);
				Case = true;

			} else {
				EteindreCase(numCaseB, EquipeB.equB.get(numCaseB).getX(),
						EquipeB.equB.get(numCaseB).getY());
				Case = false;
			}
		}

	}

	static void AllumeCase(int i, int X, int Y) {

		if (Hjoue == true) {
			// X= EquipeH.equH.get(i).getX();
			// Y= EquipeH.equH.get(i).getY();
			if (EquipeH.equH.get(i).getDame() == true) {
				try {
					EquipeH.equH
							.set(i,
									new Img(ImageIO.read(new File(
											"img/dameNoirAllume.png")), X,
											Y, true));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					EquipeH.equH
							.set(i,
									new Img(ImageIO.read(new File(
											"img/pionNoirAllume.png")), X,
											Y, false));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} else if (Hjoue == false) {
			if (EquipeB.equB.get(i).getDame() == true) {

				try {
					EquipeB.equB
							.set(i,
									new Img(ImageIO.read(new File(
											"img/dameBlancAllume.png")), X,
											Y, true));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					EquipeB.equB.set(
							i,
							new Img(ImageIO.read(new File(
									"img/pionBlancAllume.png")), X, Y,
									false));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	static void EteindreCase(int i, int X, int Y) {
		if (Hjoue == true) {
			if (EquipeH.equH.get(i).getDame() == true) {
				try {
					EquipeH.equH.set(
							i,
							new Img(ImageIO.read(new File(
									"img/dameNoir.png")), X, Y, true));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					EquipeH.equH.set(
							i,
							new Img(ImageIO.read(new File(
									"img/pionNoir.png")), X, Y, false));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} else {
			if (EquipeB.equB.get(i).getDame() == true) {

				try {
					EquipeB.equB.set(
							i,
							new Img(ImageIO.read(new File(
									"img/dameBlanc.png")), X, Y, true));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					EquipeB.equB.set(
							i,
							new Img(ImageIO.read(new File(
									"img/pionBlanc.png")), X, Y, false));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	static boolean BougerPion(int X, int Y) {
		int xH = EquipeH.equH.get(numCaseH).getX();
		int yH = EquipeH.equH.get(numCaseH).getY();
		int xB = EquipeB.equB.get(numCaseB).getX();
		int yB = EquipeB.equB.get(numCaseB).getY();

		X = (X / pion) * pion;
		Y = (Y / pion) * pion;

		if (Case == false) {
			if (Hjoue == true) {
				if (EquipeH.equH.get(numCaseH).getDame() == true) {
					if (RechercheDameH(numCaseH, X, Y) == Bouger) {

						EquipeH.equH.get(numCaseH).setX(X);
						EquipeH.equH.get(numCaseH).setY(Y);
						Hjoue = false;
						return true;
					} else if (RechercheDameH(numCaseH, X, Y) == PasBouger) {

					} else if (RechercheDameH(numCaseH, X, Y) == BougerEtPrendre) {
						EquipeH.equH.get(numCaseH).setX(X);
						EquipeH.equH.get(numCaseH).setY(Y);
						EquipeB.equB.remove(BougerEtPrendre);
						BougerEtPrendre = -3;

						int i = RecherchePionH(X, Y);
						int alert = 0;
						for (int x = X - Cote; x < Cote; x += pion) {
							for (int y = Y - Cote; y < Cote; y += pion) {
								if (x >= 0 && y >= 0) {
									if (RechercheDameH(i, x, y) == BougerEtPrendre) {
										BougerEtPrendre = -3;
										alert++;

									}
								}
							}
						}
						for (int x = X - Cote; x < Cote; x += pion) {
							for (int y = Y + Cote; y >= 0; y -= pion) {
								if (x >= 0 && y < Cote) {
									if (RechercheDameH(i, x, y) == BougerEtPrendre) {
										BougerEtPrendre = -3;
										alert++;

									}
								}
							}
						}
						System.out.println("alert: " + alert);

						if (alert == 0) {
							alert = 0;
							System.out.println("NE peut pas manger");
							Hjoue = false;
						} else {
							alert = 0;
							AllumeCase(i, X, Y);
							Case = true;
						}
						return true;

					}
				}

				else {

					if (X == xH + pion && Y == yH + pion
							&& RecherchePionBH(xH + pion, yH + pion) == -1) {
						EquipeH.equH.get(numCaseH).setX(xH + pion);
						EquipeH.equH.get(numCaseH).setY(yH + pion);
						Dame(numCaseH);
						Hjoue = false;
						return true;
					} else if (X == xH - pion && Y == yH + pion
							&& RecherchePionBH(xH - pion, yH + pion) == -1) {
						EquipeH.equH.get(numCaseH).setX(xH - pion);
						EquipeH.equH.get(numCaseH).setY(yH + pion);

						Dame(numCaseH);

						Hjoue = false;
						return true;
					} else if (X == xH + 2 * pion
							&& Y == yH + 2 * pion
							&& RecherchePionB(xH + pion, yH + pion) != -1
							&& RecherchePionBH(xH + 2 * pion, yH + 2 * pion) == -1) {
						EquipeB.equB
								.remove(RecherchePionB(xH + pion, yH + pion));
						// System.out.println(EquipeB.str());

						EquipeH.equH.get(numCaseH).setX(xH + 2 * pion);
						EquipeH.equH.get(numCaseH).setY(yH + 2 * pion);

						Dame(numCaseH);

						if (peutPrendre(X + pion * 2, Y + pion * 2, X, Y) == false
								&& peutPrendre(X - pion * 2, Y + pion * 2, X, Y) == false) {

							System.out.println("NE peut pas manger");
							Hjoue = false;
						} else {
							int i = RecherchePionH(X, Y);
							AllumeCase(i, X, Y);
							Case = true;

						}
						return true;

					} else if (X == xH - 2 * pion
							&& Y == yH + 2 * pion
							&& RecherchePionB(xH - pion, yH + pion) != -1
							&& RecherchePionBH(xH - 2 * pion, yH + 2 * pion) == -1) {
						EquipeB.equB
								.remove(RecherchePionB(xH - pion, yH + pion));
						// System.out.println(EquipeB.str());

						EquipeH.equH.get(numCaseH).setX(xH - 2 * pion);
						EquipeH.equH.get(numCaseH).setY(yH + 2 * pion);

						Dame(numCaseH);

						if (peutPrendre(X + pion * 2, Y + pion * 2, X, Y) == false
								&& peutPrendre(X - pion * 2, Y + pion * 2, X, Y) == false) {
							System.out.println("NE peut pas manger");
							Hjoue = false;
						} else {
							int i = RecherchePionH(X, Y);
							AllumeCase(i, X, Y);
							Case = true;

						}
						return true;

					}
				}

			} else {
				// System.out.println("X = " + X + "  Y = " + Y);
				// System.out.println("xH = " + xH + "  yH = " + yH);

				if (EquipeB.equB.get(numCaseB).getDame() == true) {
					if (RechercheDameB(numCaseB, X, Y) == Bouger) {
						// System.out.println("Bouger " + Bouger);

						EquipeB.equB.get(numCaseB).setX(X);
						EquipeB.equB.get(numCaseB).setY(Y);
						Hjoue = true;
						return true;
					} else if (RechercheDameB(numCaseB, X, Y) == PasBouger) {

					} else if (RechercheDameB(numCaseB, X, Y) == BougerEtPrendre) {
						// System.out.println("BougerEtPrendre " +
						// BougerEtPrendre);

						EquipeB.equB.get(numCaseB).setX(X);
						EquipeB.equB.get(numCaseB).setY(Y);
						EquipeH.equH.remove(BougerEtPrendre);
						BougerEtPrendre = -3;

						int i = RecherchePionB(X, Y);
						int alert = 0;
						for (int x = X - Cote; x < Cote; x += pion) {
							for (int y = Y - Cote; y < Cote; y += pion) {
								if (x >= 0 && y >= 0) {
									if (RechercheDameB(i, x, y) == BougerEtPrendre) {
										BougerEtPrendre = -3;
										alert++;

									}
								}
							}
						}
						for (int x = X - Cote; x < Cote; x += pion) {
							for (int y = Y + Cote; y >= 0; y -= pion) {
								if (x >= 0 && y < Cote) {
									if (RechercheDameB(i, x, y) == BougerEtPrendre) {
										BougerEtPrendre = -3;
										alert++;

									}
								}
							}
						}
						System.out.println("alert: " + alert);

						if (alert == 0) {

							System.out.println("NE peut pas manger");
							Hjoue = true;
						} else {
							alert = 0;
							AllumeCase(i, X, Y);
							Case = true;
						}
						return true;

					}
				}

				else {

					if (X == xB + pion && Y == yB - pion
							&& RecherchePionBH(xB + pion, yB - pion) == -1) {
						EquipeB.equB.get(numCaseB).setX(xB + pion);
						EquipeB.equB.get(numCaseB).setY(yB - pion);

						Dame(numCaseB);

						Hjoue = true;
						return true;
					} else if (X == xB - pion && Y == yB - pion
							&& RecherchePionBH(xB - pion, yB - pion) == -1) {
						EquipeB.equB.get(numCaseB).setX(xB - pion);
						EquipeB.equB.get(numCaseB).setY(yB - pion);

						Dame(numCaseB);

						Hjoue = true;
						return true;

					}

					else if (X == xB + 2 * pion
							&& Y == yB - 2 * pion
							&& RecherchePionH(xB + pion, yB - pion) != -1
							&& RecherchePionBH(xB + 2 * pion, yB - 2 * pion) == -1) {
						EquipeH.equH
								.remove(RecherchePionH(xB + pion, yB - pion));
						// System.out.println(EquipeH.str());

						EquipeB.equB.get(numCaseB).setX(xB + 2 * pion);
						EquipeB.equB.get(numCaseB).setY(yB - 2 * pion);

						Dame(numCaseB);

						if (peutPrendre(X + pion * 2, Y - pion * 2, X, Y) == false
								&& peutPrendre(X - pion * 2, Y - pion * 2, X, Y) == false) {

							System.out.println("NE peut pas manger");
							Hjoue = true;
						} else {
							int i = RecherchePionB(X, Y);
							AllumeCase(i, X, Y);
							Case = true;

						}
						return true;

					} else if (X == xB - 2 * pion
							&& Y == yB - 2 * pion
							&& RecherchePionH(xB - pion, yB - pion) != -1
							&& RecherchePionBH(xB - 2 * pion, yB - 2 * pion) == -1) {
						EquipeH.equH
								.remove(RecherchePionH(xB - pion, yB - pion));
						// System.out.println(EquipeH.str());

						EquipeB.equB.get(numCaseB).setX(xB - 2 * pion);
						EquipeB.equB.get(numCaseB).setY(yB - 2 * pion);

						Dame(numCaseB);

						if (peutPrendre(X + pion * 2, Y - pion * 2, X, Y) == false
								&& peutPrendre(X - pion * 2, Y - pion * 2, X, Y) == false) {

							System.out.println("NE peut pas manger");
							Hjoue = true;
						} else {
							int i = RecherchePionB(X, Y);
							AllumeCase(i, X, Y);
							Case = true;

						}
						return true;
					}
				}

			}

		}

		return false;
	}

	static int RecherchePionH(int X, int Y) {
		X = (X / pion) * pion;
		Y = (Y / pion) * pion;
		// System.out.println("X = " + X + "  Y = " + Y);

		for (int i = 0; i < EquipeH.equH.size(); i++) {
			if (EquipeH.equH.get(i).getX() == X
					&& EquipeH.equH.get(i).getY() == Y) {
				return i;
			}
		}

		return -1;
	}

	static int RecherchePionB(int X, int Y) {
		X = (X / pion) * pion;
		Y = (Y / pion) * pion;
		// System.out.println("X = " + X + "  Y = " + Y);

		for (int i = 0; i < EquipeB.equB.size(); i++) {
			if (EquipeB.equB.get(i).getX() == X
					&& EquipeB.equB.get(i).getY() == Y) {
				return i;
			}

		}

		return -1;
	}

	static int RecherchePionBH(int X, int Y) {
		X = (X / pion) * pion;
		Y = (Y / pion) * pion;
		// System.out.println("X = " + X + "  Y = " + Y);

		for (int i = 0; i < EquipeH.equH.size(); i++) {
			if (EquipeH.equH.get(i).getX() == X
					&& EquipeH.equH.get(i).getY() == Y) {
				return i;
			}
		}

		for (int i = 0; i < EquipeB.equB.size(); i++) {
			if (EquipeB.equB.get(i).getX() == X
					&& EquipeB.equB.get(i).getY() == Y) {
				return i;
			}

		}

		return -1;
	}

	static void Dame(int i) {

		if (Hjoue == true) {
			if (EquipeH.equH.get(i).getY() == 7 * pion) {

				try {
					EquipeH.equH.get(i).setImg(
							ImageIO.read(new File("img/dameNoir.png")));
				} catch (IOException e) {
					e.printStackTrace();
				}
				EquipeH.equH.get(i).setDame(true);

			}
		} else {
			if (EquipeB.equB.get(i).getY() == 0) {

				try {
					EquipeB.equB.get(i).setImg(
							ImageIO.read(new File("img/dameBlanc.png")));
				} catch (IOException e) {
					e.printStackTrace();
				}
				EquipeB.equB.get(i).setDame(true);

			}
		}

	}

	static int RechercheDameH(int numCaseH, int X, int Y) {

		int xH = EquipeH.equH.get(numCaseH).getX();
		int yH = EquipeH.equH.get(numCaseH).getY();
		int N = 0;
		int count = 1;
		int count2 = 0;

		X = (X / pion) * pion;
		Y = (Y / pion) * pion;

		// System.out.println("numcase " + numCaseH);

		if (EquipeH.equH.get(numCaseH).getDame() == false) {
			return PasDame;
		} else {
			if (Math.abs(X - xH) == Math.abs(Y - yH)) {
				N = Math.abs(X - xH) / pion;
				// System.out.println("Nh " + N);
			}

			else
				return PasBouger;

			// System.out.println("X " + X + "Y " + Y);
			// System.out.println("xH " + xH + "yH " + yH);

			if (X > xH && Y > yH) {
				for (int i = 1; i < N + 1; i++) {
					if (RecherchePionBH(xH + pion * i, yH + pion * i) == -1)
						count++;
					else if (RecherchePionH(xH + pion * i, yH + pion * i) != -1)
						return PasBouger;
					else if (RecherchePionB(xH + pion * i, yH + pion * i) != -1) {
						count2++;
						BougerEtPrendre = RecherchePionB(xH + pion * i, yH
								+ pion * i);
					}

				}
				// System.out.println("count " + count);
				// System.out.println("count2 " + count2);

				if (count == N + 1)
					return Bouger;
				else if (count2 == 1
						&& RecherchePionBH(xH + pion * N, yH + pion * N) == -1)
					return BougerEtPrendre;
				else if (count2 > 1)
					return PasBouger;
			}

			else if (X > xH && Y < yH) {
				for (int i = 1; i < N + 1; i++) {
					if (RecherchePionBH(xH + pion * i, yH - pion * i) == -1)
						count++;
					else if (RecherchePionH(xH + pion * i, yH - pion * i) != -1)
						return PasBouger;
					else if (RecherchePionB(xH + pion * i, yH - pion * i) != -1) {
						count2++;
						BougerEtPrendre = RecherchePionB(xH + pion * i, yH
								- pion * i);

					}
				}
				// System.out.println("count " + count);
				// System.out.println("count2 " + count2);

				if (count == N + 1)
					return Bouger;
				else if (count2 == 1
						&& RecherchePionBH(xH + pion * N, yH - pion * N) == -1)
					return BougerEtPrendre;
				else if (count2 > 1)
					return PasBouger;

			}

			else if (X < xH && Y > yH) {
				for (int i = 1; i < N + 1; i++) {
					if (RecherchePionBH(xH - pion * i, yH + pion * i) == -1)
						count++;
					else if (RecherchePionH(xH - pion * i, yH + pion * i) != -1)
						return PasBouger;
					else if (RecherchePionB(xH - pion * i, yH + pion * i) != -1) {
						count2++;
						BougerEtPrendre = RecherchePionB(xH - pion * i, yH
								+ pion * i);
					}
				}
				// System.out.println("count " + count);
				// System.out.println("count2 " + count2);

				if (count == N + 1)
					return Bouger;
				else if (count2 == 1
						&& RecherchePionBH(xH - pion * N, yH + pion * N) == -1)
					return BougerEtPrendre;
				else if (count2 > 1)
					return PasBouger;

			}

			else if (X < xH && Y < yH) {
				for (int i = 1; i < N + 1; i++) {
					// System.out.println("Recherche H "+ RecherchePionH(xH -
					// pion * i, yH - pion * i));
					if (RecherchePionBH(xH - pion * i, yH - pion * i) == -1)
						count++;
					else if (RecherchePionH(xH - pion * i, yH - pion * i) != -1)
						return PasBouger;
					else if (RecherchePionB(xH - pion * i, yH - pion * i) != -1) {
						count2++;
						BougerEtPrendre = RecherchePionB(xH - pion * i, yH
								- pion * i);
					}
				}
				// System.out.println("count " + count);
				// System.out.println("count2 " + count2);

				if (count == N + 1)
					return Bouger;
				else if (count2 == 1
						&& RecherchePionBH(xH - pion * N, yH - pion * N) == -1)
					return BougerEtPrendre;
				else if (count2 > 1)
					return PasBouger;

			}
		}
		return PasBouger;
	}

	static int RechercheDameB(int numCaseB, int X, int Y) {

		int xB = EquipeB.equB.get(numCaseB).getX();
		int yB = EquipeB.equB.get(numCaseB).getY();
		int N = 0;
		int count = 1;
		int count2 = 0;

		X = (X / pion) * pion;
		Y = (Y / pion) * pion;

		if (EquipeB.equB.get(numCaseB).getDame() == false) {
			return PasDame;
		} else {
			if (Math.abs(X - xB) == Math.abs(Y - yB))
				N = Math.abs(X - xB) / pion;
			else
				return PasBouger;

			if (X > xB && Y > yB) {
				for (int i = 1; i < N + 1; i++) {
					if (RecherchePionBH(xB + pion * i, yB + pion * i) == -1)
						count++;
					else if (RecherchePionB(xB + pion * i, yB + pion * i) != -1)
						return PasBouger;
					else if (RecherchePionH(xB + pion * i, yB + pion * i) != -1) {
						count2++;
						BougerEtPrendre = RecherchePionH(xB + pion * i, yB
								+ pion * i);
					}
				}

				if (count == N + 1)
					return Bouger;
				else if (count2 == 1
						&& RecherchePionBH(xB + pion * N, yB + pion * N) == -1)
					return BougerEtPrendre;
				else if (count2 > 1)
					return PasBouger;
			}

			else if (X > xB && Y < yB) {
				for (int i = 1; i < N + 1; i++) {
					if (RecherchePionBH(xB + pion * i, yB - pion * i) == -1)
						count++;
					else if (RecherchePionB(xB + pion * i, yB - pion * i) != -1)
						return PasBouger;
					else if (RecherchePionH(xB + pion * i, yB - pion * i) != -1) {
						count2++;
						BougerEtPrendre = RecherchePionH(xB + pion * i, yB
								- pion * i);
					}
				}

				if (count == N + 1)
					return Bouger;
				else if (count2 == 1
						&& RecherchePionBH(xB + pion * N, yB - pion * N) == -1)
					return BougerEtPrendre;
				else if (count2 > 1)
					return PasBouger;

			}

			else if (X < xB && Y > yB) {
				for (int i = 1; i < N + 1; i++) {
					if (RecherchePionBH(xB - pion * i, yB + pion * i) == -1)
						count++;
					else if (RecherchePionB(xB - pion * i, yB + pion * i) != -1)
						return PasBouger;
					else if (RecherchePionH(xB - pion * i, yB + pion * i) != -1) {
						count2++;
						BougerEtPrendre = RecherchePionH(xB - pion * i, yB
								+ pion * i);
					}
				}

				if (count == N + 1)
					return Bouger;
				else if (count2 == 1
						&& RecherchePionBH(xB - pion * N, yB + pion * N) == -1)
					return BougerEtPrendre;
				else if (count2 > 1)
					return PasBouger;

			}

			else if (X < xB && Y < yB) {
				for (int i = 1; i < N + 1; i++) {
					if (RecherchePionBH(xB - pion * i, yB - pion * i) == -1)
						count++;
					else if (RecherchePionB(xB - pion * i, yB - pion * i) != -1)
						return PasBouger;
					else if (RecherchePionH(xB - pion * i, yB - pion * i) != -1) {
						count2++;
						BougerEtPrendre = RecherchePionH(xB - pion * i, yB
								- pion * i);
					}
				}

				if (count == N + 1)
					return Bouger;
				else if (count2 == 1
						&& RecherchePionBH(xB - pion * N, yB - pion * N) == -1)
					return BougerEtPrendre;
				else if (count2 > 1)
					return PasBouger;

			}
		}

		return PasBouger;
	}

	// X,Y ou je veux aller, x,y ou je suis
	static boolean peutPrendre(int X, int Y, int x, int y) {

		int xH = x;
		int yH = y;
		int xB = x;
		int yB = y;

		if (X < 0 || Y < 0 || X > 399 || Y > 399) {
			return false;
		}
		if (Hjoue == true) {
			// System.out.println("X = " + X + "  Y = " + Y);
			// System.out.println("xH = " + xH + "  yH = " + yH);

			// System.out.println("CASEDAYEM "+ RechercheDameH(numCaseH, X,
			// Y));
			int i = RecherchePionH(x, y);
			if (EquipeH.equH.get(i).getDame() == true) {

				if (RechercheDameH(i, X, Y) == BougerEtPrendre) {
					BougerEtPrendre = -3;
					return true;
				}
			}

			else {
				if (X == xH + 2 * pion && Y == yH + 2 * pion
						&& RecherchePionB(xH + pion, yH + pion) != -1
						&& RecherchePionBH(xH + 2 * pion, yH + 2 * pion) == -1) {
					return true;

				} else if (X == xH - 2 * pion && Y == yH + 2 * pion
						&& RecherchePionB(xH - pion, yH + pion) != -1
						&& RecherchePionBH(xH - 2 * pion, yH + 2 * pion) == -1) {
					return true;

				}
			}

		} else {
			// System.out.println("X = " + X + "  Y = " + Y);
			// System.out.println("xB = " + xB + "  yB = " + yB);

			int i = RecherchePionB(x, y);
			if (EquipeB.equB.get(i).getDame() == true) {
				if (RechercheDameB(i, X, Y) == BougerEtPrendre) {
					BougerEtPrendre = -3;
					return true;

				}
			}

			else {

				if (X == xB + 2 * pion && Y == yB - 2 * pion
						&& RecherchePionH(xB + pion, yB - pion) != -1
						&& RecherchePionBH(xB + 2 * pion, yB - 2 * pion) == -1) {
					return true;

				} else if (X == xB - 2 * pion && Y == yB - 2 * pion
						&& RecherchePionH(xB - pion, yB - pion) != -1
						&& RecherchePionBH(xB - 2 * pion, yB - 2 * pion) == -1) {
					return true;
				}
			}

		}
		return false;
	}

	static boolean peutPrendre(int i) {

		if (Hjoue == true) {
			int xH = EquipeH.equH.get(i).getX();
			int yH = EquipeH.equH.get(i).getY();
			if (EquipeH.equH.get(i).getDame() == true) {

				for (int j = 1; j < 8; j++) {
					if (RechercheDameH(i, xH + pion * j, yH + pion * j) == BougerEtPrendre
							&& xH + pion * j <= 7 * pion
							&& yH + pion * j <= 7 * pion) {
						BougerEtPrendre = -3;
						return true;
					} else if (RechercheDameH(i, xH + pion * j, yH - pion * j) == BougerEtPrendre
							&& xH + pion * j <= 7 * pion && yH - pion * j >= 0) {
						BougerEtPrendre = -3;
						return true;
					} else if (RechercheDameH(i, xH - pion * j, yH + pion * j) == BougerEtPrendre
							&& xH - pion * j >= 0 && yH + pion * j <= 7 * pion) {
						BougerEtPrendre = -3;
						return true;
					} else if (RechercheDameH(i, xH - pion * j, yH - pion * j) == BougerEtPrendre
							&& xH - pion * j >= 0 && yH - pion * j >= 0) {
						BougerEtPrendre = -3;
						return true;
					}

				}

			} else if (EquipeH.equH.get(i).getDame() == false) {
				if (peutPrendre(xH + 2 * pion, yH + 2 * pion, xH, yH)
						|| peutPrendre(xH - 2 * pion, yH + 2 * pion, xH, yH)) {
					return true;
				}

			}
		} else if (Hjoue == false) {
			int xB = EquipeB.equB.get(i).getX();
			int yB = EquipeB.equB.get(i).getY();
			if (EquipeB.equB.get(i).getDame() == true) {

				for (int j = 1; j < 8; j++) {
					if (RechercheDameB(i, xB + pion * j, yB + pion * j) == BougerEtPrendre
							&& xB + pion * j <= 7 * pion
							&& yB + pion * j <= 7 * pion) {
						BougerEtPrendre = -3;
						return true;
					} else if (RechercheDameB(i, xB + pion * j, yB - pion * j) == BougerEtPrendre
							&& xB + pion * j <= 7 * pion && yB - pion * j >= 0) {
						BougerEtPrendre = -3;
						return true;
					} else if (RechercheDameB(i, xB - pion * j, yB + pion * j) == BougerEtPrendre
							&& xB - pion * j >= 0 && yB + pion * j <= 7 * pion) {
						BougerEtPrendre = -3;
						return true;
					} else if (RechercheDameB(i, xB - pion * j, yB - pion * j) == BougerEtPrendre
							&& xB - pion * j >= 0 && yB - pion * j >= 0) {
						BougerEtPrendre = -3;
						return true;
					}

				}

			} else if (EquipeB.equB.get(i).getDame() == false) {
				if (peutPrendre(xB + 2 * pion, yB - 2 * pion, xB, yB)
						|| peutPrendre(xB - 2 * pion, yB - 2 * pion, xB, yB)) {
					return true;
				}
			}
		}
		return false;

	}

	static boolean peutPrendreADroite(int i) {

		if (Hjoue == true) {
			System.out.println("peut manger A droite H");
			int xH = EquipeH.equH.get(i).getX();
			int yH = EquipeH.equH.get(i).getY();
			if (EquipeH.equH.get(i).getDame() == true) {

				for (int j = 0; j < 8; j++) {
					if (RechercheDameH(i, xH + pion * j, yH + pion * j) == BougerEtPrendre
							&& xH + pion * j <= 7 * pion
							&& yH + pion * j <= 7 * pion) {
						BougerEtPrendre = -3;
						return true;
					} else if (RechercheDameH(i, xH + pion * j, yH - pion * j) == BougerEtPrendre
							&& xH + pion * j <= 7 * pion && yH - pion * j >= 0) {
						BougerEtPrendre = -3;
						return true;
					}

				}

			} else if (EquipeH.equH.get(i).getDame() == false) {
				if (peutPrendre(xH + 2 * pion, yH + 2 * pion, xH, yH)) {
					return true;
				}

			}
		} else if (Hjoue == false) {
			System.out.println("peut manger A droite B");
			int xB = EquipeB.equB.get(i).getX();
			int yB = EquipeB.equB.get(i).getY();
			if (EquipeB.equB.get(i).getDame() == true) {

				for (int j = 1; j < 8; j++) {
					if (RechercheDameB(i, xB + pion * j, yB + pion * j) == BougerEtPrendre
							&& xB + pion * j <= 7 * pion
							&& yB + pion * j <= 7 * pion) {
						BougerEtPrendre = -3;
						return true;
					} else if (RechercheDameB(i, xB + pion * j, yB - pion * j) == BougerEtPrendre
							&& xB + pion * j <= 7 * pion && yB - pion * j >= 0) {
						BougerEtPrendre = -3;
						return true;
					}

				}

			} else if (EquipeB.equB.get(i).getDame() == false) {
				if (peutPrendre(xB + 2 * pion, yB - 2 * pion, xB, yB)) {
					return true;
				}
			}
		}
		return false;

	}

	static boolean peutPrendreAGauche(int i) {

		if (Hjoue == true) {
			System.out.println("peut manger A gauche H");
			int xH = EquipeH.equH.get(i).getX();
			int yH = EquipeH.equH.get(i).getY();
			if (EquipeH.equH.get(i).getDame() == true) {

				for (int j = 0; j < 8; j++) {
					if (RechercheDameH(i, xH - pion * j, yH + pion * j) == BougerEtPrendre
							&& xH - pion * j >= 0 && yH + pion * j <= 7 * pion) {
						BougerEtPrendre = -3;
						return true;
					} else if (RechercheDameH(i, xH - pion * j, yH - pion * j) == BougerEtPrendre
							&& xH - pion * j >= 0 && yH - pion * j >= 0) {
						BougerEtPrendre = -3;
						return true;
					}

				}

			} else if (EquipeH.equH.get(i).getDame() == false) {
				if (peutPrendre(xH - 2 * pion, yH + 2 * pion, xH, yH)) {
					return true;
				}

			}
		} else if (Hjoue == false) {
			System.out.println("peut manger A gauche B");
			int xB = EquipeB.equB.get(i).getX();
			int yB = EquipeB.equB.get(i).getY();
			if (EquipeB.equB.get(i).getDame() == true) {

				for (int j = 1; j < 8; j++) {
					if (RechercheDameB(i, xB - pion * j, yB + pion * j) == BougerEtPrendre
							&& xB - pion * j >= 0 && yB + pion * j <= 7 * pion) {
						BougerEtPrendre = -3;
						return true;
					} else if (RechercheDameB(i, xB - pion * j, yB - pion * j) == BougerEtPrendre
							&& xB - pion * j >= 0 && yB - pion * j >= 0) {
						BougerEtPrendre = -3;
						return true;
					}

				}

			} else if (EquipeB.equB.get(i).getDame() == false) {
				if (peutPrendre(xB - 2 * pion, yB - 2 * pion, xB, yB)) {
					return true;
				}
			}
		}
		return false;

	}

	static boolean peutBouger(int i) {

		if (Hjoue == true) {
			int xH = EquipeH.equH.get(i).getX();
			int yH = EquipeH.equH.get(i).getY();
			if (EquipeH.equH.get(i).getDame() == true) {

				for (int j = 1; j < 8; j++) {
					if (RechercheDameH(i, xH + pion * j, yH + pion * j) == Bouger
							&& xH + pion * j <= 7 * pion
							&& yH + pion * j <= 7 * pion) {
						return true;
					} else if (RechercheDameH(i, xH + pion * j, yH - pion * j) == Bouger
							&& xH + pion * j <= 7 * pion && yH - pion * j >= 0) {
						return true;
					} else if (RechercheDameH(i, xH - pion * j, yH + pion * j) == Bouger
							&& xH - pion * j >= 0 && yH + pion * j <= 7 * pion) {
						return true;
					} else if (RechercheDameH(i, xH - pion * j, yH - pion * j) == Bouger
							&& xH - pion * j >= 0 && yH + pion * j >= 0) {
						return true;
					}

				}

			} else if (EquipeH.equH.get(i).getDame() == false) {
				if ((RecherchePionBH(xH + pion, yH + pion) == -1
						&& xH + pion <= 7 * pion && yH + pion <= 7 * pion)
						|| (RecherchePionBH(xH - pion, yH + pion) == -1
								&& xH - pion >= 0 && yH + pion <= 7 * pion)) {
					return true;
				}
			}
		} else if (Hjoue == false) {
			int xB = EquipeB.equB.get(i).getX();
			int yB = EquipeB.equB.get(i).getY();
			if (EquipeB.equB.get(i).getDame() == true) {

				for (int j = 1; j < 8; j++) {
					if (RechercheDameB(i, xB + pion * j, yB + pion * j) == Bouger
							&& xB + pion * j <= 7 * pion
							&& yB + pion * j <= 7 * pion) {
						return true;
					} else if (RechercheDameB(i, xB + pion * j, yB - pion * j) == Bouger
							&& xB + pion * j <= 7 * pion && yB - pion * j >= 0) {
						return true;
					} else if (RechercheDameB(i, xB - pion * j, yB + pion * j) == Bouger
							&& xB - pion * j >= 0 && yB + pion * j <= 7 * pion) {
						return true;
					} else if (RechercheDameB(i, xB - pion * j, yB - pion * j) == Bouger
							&& xB - pion * j >= 0 && yB - pion * j >= 0) {
						return true;
					}

				}

			} else if (EquipeB.equB.get(i).getDame() == false) {
				if ((RecherchePionBH(xB + pion, yB - pion) == -1
						&& xB + pion <= 7 * pion && yB - pion >= 0)
						|| (RecherchePionBH(xB - pion, yB - pion) == -1
								&& xB - pion >= 0 && yB - pion >= 0)) {
					return true;
				}
			}

		}

		return false;
	}

	static boolean peutBougerADroite(int i) {

		if (Hjoue == true) {
			System.out.println("peut bouger A droite H");
			int xH = EquipeH.equH.get(i).getX();
			int yH = EquipeH.equH.get(i).getY();
			if (EquipeH.equH.get(i).getDame() == true) {

				for (int j = 1; j < 8; j++) {
					if (RechercheDameH(i, xH + pion * j, yH + pion * j) == Bouger
							&& xH + pion * j <= 7 * pion
							&& yH + pion * j <= 7 * pion) {
						return true;
					} else if (RechercheDameH(i, xH + pion * j, yH - pion * j) == Bouger
							&& xH + pion * j <= 7 * pion && yH - pion * j >= 0) {
						return true;
					}

				}

			} else if (EquipeH.equH.get(i).getDame() == false) {
				if ((RecherchePionBH(xH + pion, yH + pion) == -1
						&& xH + pion <= 7 * pion && yH + pion <= 7 * pion)) {
					return true;
				}
			}
		} else if (Hjoue == false) {
			System.out.println("peut bouger A droite B");
			int xB = EquipeB.equB.get(i).getX();
			int yB = EquipeB.equB.get(i).getY();
			if (EquipeB.equB.get(i).getDame() == true) {

				for (int j = 1; j < 8; j++) {
					if (RechercheDameB(i, xB + pion * j, yB + pion * j) == Bouger
							&& xB + pion * j <= 7 * pion
							&& yB + pion * j <= 7 * pion) {
						return true;
					} else if (RechercheDameB(i, xB + pion * j, yB - pion * j) == Bouger
							&& xB + pion * j <= 7 * pion && yB - pion * j >= 0) {
						return true;
					}

				}

			} else if (EquipeB.equB.get(i).getDame() == false) {
				if ((RecherchePionBH(xB + pion, yB - pion) == -1
						&& xB + pion <= 7 * pion && yB - pion >= 0)) {
					return true;
				}
			}

		}

		return false;
	}

	static boolean peutBougerAGauche(int i) {

		if (Hjoue == true) {
			System.out.println("peut bouger A gauche H");
			int xH = EquipeH.equH.get(i).getX();
			int yH = EquipeH.equH.get(i).getY();
			if (EquipeH.equH.get(i).getDame() == true) {

				for (int j = 1; j < 8; j++) {
					if (RechercheDameH(i, xH - pion * j, yH + pion * j) == Bouger
							&& xH - pion * j >= 0 && yH + pion * j <= 7 * pion) {
						return true;
					} else if (RechercheDameH(i, xH - pion * j, yH - pion * j) == Bouger
							&& xH - pion * j >= 0 && yH + pion * j >= 0) {
						return true;
					}

				}

			} else if (EquipeH.equH.get(i).getDame() == false) {
				if ((RecherchePionBH(xH - pion, yH + pion) == -1
						&& xH - pion >= 0 && yH + pion <= 7 * pion)) {
					return true;
				}
			}
		} else if (Hjoue == false) {
			System.out.println("peut bouger A gauche B");
			int xB = EquipeB.equB.get(i).getX();
			int yB = EquipeB.equB.get(i).getY();
			if (EquipeB.equB.get(i).getDame() == true) {

				for (int j = 1; j < 8; j++) {
					if (RechercheDameB(i, xB - pion * j, yB + pion * j) == Bouger
							&& xB - pion * j >= 0 && yB + pion * j <= 7 * pion) {
						return true;
					} else if (RechercheDameB(i, xB - pion * j, yB - pion * j) == Bouger
							&& xB - pion * j >= 0 && yB - pion * j >= 0) {
						return true;
					}

				}

			} else if (EquipeB.equB.get(i).getDame() == false) {
				if ((RecherchePionBH(xB - pion, yB - pion) == -1
						&& xB - pion >= 0 && yB - pion >= 0)) {
					return true;
				}
			}

		}

		return false;
	}

	static void mangerADroite(int i) {

		if (Hjoue == true) {
			System.out.println("MANGER A droite H");
			int xH = EquipeH.equH.get(i).getX();
			int yH = EquipeH.equH.get(i).getY();
			if (EquipeH.equH.get(i).getDame() == true) {
				ArrayList<Integer> liste1 = new ArrayList<Integer>();
				ArrayList<Integer> liste2 = new ArrayList<Integer>();

				for (int j = 1; j < 8; j++) {
					if (RechercheDameH(i, xH + pion * j, yH + pion * j) == BougerEtPrendre
							&& xH + pion * j <= 7 * pion
							&& yH + pion * j <= 7 * pion) {
						BougerEtPrendre = -3;
						liste1.add(j);
					}
					if (RechercheDameH(i, xH + pion * j, yH - pion * j) == BougerEtPrendre
							&& xH + pion * j <= 7 * pion && yH - pion * j >= 0) {
						BougerEtPrendre = -3;
						liste2.add(j);
					}

				}

				int N = (int) (Math.random() * (liste1.size() + liste2.size()) + 1);
				if (N <= liste1.size()) {
					int j = liste1.get(N - 1);
					EquipeB.equB.remove(RechercheDameH(i, xH + pion * j, yH
							+ pion * j));
					EquipeH.equH.get(i).setX(xH + pion * j);
					EquipeH.equH.get(i).setY(yH + pion * j);
				} else if (N > liste1.size()) {
					int j = liste2.get(N - 1 - liste1.size());
					EquipeB.equB.remove(RechercheDameH(i, xH + pion * j, yH
							- pion * j));
					EquipeH.equH.get(i).setX(xH + pion * j);
					EquipeH.equH.get(i).setY(yH - pion * j);
				}

			} else if (EquipeH.equH.get(i).getDame() == false) {
				EquipeH.equH.get(i).setX(xH + 2 * pion);
				EquipeH.equH.get(i).setY(yH + 2 * pion);
				int a = RecherchePionB(xH + pion, yH + pion);
				EquipeB.equB.remove(a);
			}
		} else if (Hjoue == false) {
			System.out.println("MANGER A droite B");
			int xB = EquipeB.equB.get(i).getX();
			int yB = EquipeB.equB.get(i).getY();
			if (EquipeB.equB.get(i).getDame() == true) {

				ArrayList<Integer> liste1 = new ArrayList<Integer>();
				ArrayList<Integer> liste2 = new ArrayList<Integer>();

				for (int j = 1; j < 8; j++) {
					if (RechercheDameB(i, xB + pion * j, yB + pion * j) == BougerEtPrendre
							&& xB + pion * j <= 7 * pion
							&& yB + pion * j <= 7 * pion) {
						BougerEtPrendre = -3;
						liste1.add(j);
					}
					if (RechercheDameB(i, xB + pion * j, yB - pion * j) == BougerEtPrendre
							&& xB + pion * j <= 7 * pion && yB - pion * j >= 0) {
						BougerEtPrendre = -3;
						liste2.add(j);
					}

				}

				int N = (int) (Math.random() * (liste1.size() + liste2.size()) + 1);

				System.out.println("N fin " + N);
				System.out.println("liste1 fin " + liste1);
				System.out.println("liste2 fin " + liste2);
				if (N <= liste1.size()) {
					int j = liste1.get(N - 1);
					System.out.println("j fin " + j);
					System.out.println("xB fin " + xB);
					System.out.println("yB fin " + yB);
					EquipeH.equH.remove(RechercheDameB(i, xB + pion * j, yB
							+ pion * j));
					EquipeB.equB.get(i).setX(xB + pion * j);
					EquipeB.equB.get(i).setY(yB + pion * j);
				} else if (N > liste1.size()) {
					int j = liste2.get(N - 1 - liste1.size());
					EquipeH.equH.remove(RechercheDameB(i, xB + pion * j, yB
							- pion * j));
					EquipeB.equB.get(i).setX(xB + pion * j);
					EquipeB.equB.get(i).setY(yB - pion * j);
				}

			} else if (EquipeB.equB.get(i).getDame() == false) {
				EquipeB.equB.get(i).setX(xB + 2 * pion);
				EquipeB.equB.get(i).setY(yB - 2 * pion);
				int a = RecherchePionH(xB + pion, yB - pion);
				EquipeH.equH.remove(a);
			}

		}
	}

	static void mangerAGauche(int i) {

		if (Hjoue == true) {
			System.out.println("MANGER A gauche H");
			int xH = EquipeH.equH.get(i).getX();
			int yH = EquipeH.equH.get(i).getY();
			if (EquipeH.equH.get(i).getDame() == true) {
				ArrayList<Integer> liste1 = new ArrayList<Integer>();
				ArrayList<Integer> liste2 = new ArrayList<Integer>();

				for (int j = 1; j < 8; j++) {
					if (RechercheDameH(i, xH - pion * j, yH + pion * j) == BougerEtPrendre
							&& xH - pion * j >= 0 && yH + pion * j <= 7 * pion) {
						BougerEtPrendre = -3;
						liste1.add(j);
					}
					if (RechercheDameH(i, xH - pion * j, yH - pion * j) == BougerEtPrendre
							&& xH - pion * j >= 0 && yH - pion * j >= 0) {
						BougerEtPrendre = -3;
						liste2.add(j);
					}

				}

				int N = (int) (Math.random() * (liste1.size() + liste2.size()) + 1);

				if (N <= liste1.size()) {
					int j = liste1.get(N - 1);
					EquipeB.equB.remove(RechercheDameH(i, xH - pion * j, yH
							+ pion * j));
					EquipeH.equH.get(i).setX(xH - pion * j);
					EquipeH.equH.get(i).setY(yH + pion * j);
				} else if (N > liste1.size()) {
					int j = liste2.get(N - 1 - liste1.size());
					EquipeB.equB.remove(RechercheDameH(i, xH - pion * j, yH
							- pion * j));
					EquipeH.equH.get(i).setX(xH - pion * j);
					EquipeH.equH.get(i).setY(yH - pion * j);
				}

			} else if (EquipeH.equH.get(i).getDame() == false) {
				EquipeH.equH.get(i).setX(xH - 2 * pion);
				EquipeH.equH.get(i).setY(yH + 2 * pion);
				int a = RecherchePionB(xH - pion, yH + pion);
				EquipeB.equB.remove(a);
			}
		} else if (Hjoue == false) {
			System.out.println("MANGER A gauche B");
			int xB = EquipeB.equB.get(i).getX();
			int yB = EquipeB.equB.get(i).getY();
			if (EquipeB.equB.get(i).getDame() == true) {
				ArrayList<Integer> liste1 = new ArrayList<Integer>();
				ArrayList<Integer> liste2 = new ArrayList<Integer>();

				for (int j = 1; j < 8; j++) {
					if (RechercheDameB(i, xB - pion * j, yB + pion * j) == BougerEtPrendre
							&& xB - pion * j >= 0 && yB + pion * j <= 7 * pion) {
						BougerEtPrendre = -3;
						liste1.add(j);
					}
					if (RechercheDameB(i, xB - pion * j, yB - pion * j) == BougerEtPrendre
							&& xB - pion * j >= 0 && yB - pion * j >= 0) {
						BougerEtPrendre = -3;
						liste2.add(j);
					}

				}

				int N = (int) (Math.random() * (liste1.size() + liste2.size()) + 1);

				System.out.println("N fin " + N);
				System.out.println("liste1 fin " + liste1);
				System.out.println("liste2 fin " + liste2);
				if (N <= liste1.size()) {
					int j = liste1.get(N - 1);
					System.out.println("j fin " + j);
					System.out.println("xB fin " + xB);
					System.out.println("yB fin " + yB);
					EquipeH.equH.remove(RechercheDameB(i, xB - pion * j, yB
							+ pion * j));
					EquipeB.equB.get(i).setX(xB - pion * j);
					EquipeB.equB.get(i).setY(yB + pion * j);
				} else if (N > liste1.size()) {
					int j = liste2.get(N - 1 - liste1.size());
					EquipeH.equH.remove(RechercheDameB(i, xB - pion * j, yB
							- pion * j));
					EquipeB.equB.get(i).setX(xB - pion * j);
					EquipeB.equB.get(i).setY(yB - pion * j);
				}

			} else if (EquipeB.equB.get(i).getDame() == false) {
				EquipeB.equB.get(i).setX(xB - 2 * pion);
				EquipeB.equB.get(i).setY(yB - 2 * pion);
				int a = RecherchePionH(xB - pion, yB - pion);
				EquipeH.equH.remove(a);
			}

		}

	}

	static void bougerADroite(int i) {

		if (Hjoue == true) {
			System.out.println("bouger A droite H");
			int xH = EquipeH.equH.get(i).getX();
			int yH = EquipeH.equH.get(i).getY();
			if (EquipeH.equH.get(i).getDame() == true) {

				ArrayList<Integer> liste1 = new ArrayList<Integer>();
				ArrayList<Integer> liste2 = new ArrayList<Integer>();

				for (int j = 1; j < 8; j++) {
					if (RechercheDameH(i, xH + pion * j, yH + pion * j) == Bouger
							&& xH + pion * j <= 7 * pion
							&& yH + pion * j <= 7 * pion) {
						liste1.add(j);
					}
					if (RechercheDameH(i, xH + pion * j, yH - pion * j) == Bouger
							&& xH + pion * j <= 7 * pion && yH - pion * j >= 0) {
						liste2.add(j);
					}

				}
				int N = (int) (Math.random() * (liste1.size() + liste2.size()) + 1);
				if (N <= liste1.size()) {
					int j = liste1.get(N - 1);
					EquipeH.equH.get(i).setX(xH + pion * j);
					EquipeH.equH.get(i).setY(yH + pion * j);
				} else if (N > liste1.size()) {
					int j = liste2.get(N - 1 - liste1.size());
					EquipeH.equH.get(i).setX(xH + pion * j);
					EquipeH.equH.get(i).setY(yH - pion * j);
				}

			} else if (EquipeH.equH.get(i).getDame() == false) {
				EquipeH.equH.get(i).setX(xH + pion);
				EquipeH.equH.get(i).setY(yH + pion);

			}
		} else if (Hjoue == false) {
			System.out.println("bouger A droite B");
			int xB = EquipeB.equB.get(i).getX();
			int yB = EquipeB.equB.get(i).getY();
			if (EquipeB.equB.get(i).getDame() == true) {
				ArrayList<Integer> liste1 = new ArrayList<Integer>();
				ArrayList<Integer> liste2 = new ArrayList<Integer>();

				for (int j = 1; j < 8; j++) {
					if (RechercheDameB(i, xB + pion * j, yB + pion * j) == Bouger
							&& xB + pion * j <= 7 * pion
							&& yB + pion * j <= 7 * pion) {
						liste1.add(j);
					}
					if (RechercheDameB(i, xB + pion * j, yB - pion * j) == Bouger
							&& xB + pion * j <= 7 * pion && yB - pion * j >= 0) {
						liste2.add(j);
					}

				}

				int N = (int) (Math.random() * (liste1.size() + liste2.size()) + 1);
				System.out.println("N fin " + N);
				System.out.println("liste1 fin " + liste1);
				System.out.println("liste2 fin " + liste2);
				if (N <= liste1.size()) {
					int j = liste1.get(N - 1);
					System.out.println("j fin " + j);
					System.out.println("xB fin " + xB);
					System.out.println("yB fin " + yB);
					EquipeB.equB.get(i).setX(xB + pion * j);
					EquipeB.equB.get(i).setY(yB + pion * j);
				} else if (N > liste1.size()) {
					int j = liste2.get(N - 1 - liste1.size());
					EquipeB.equB.get(i).setX(xB + pion * j);
					EquipeB.equB.get(i).setY(yB - pion * j);
				}

			} else if (EquipeB.equB.get(i).getDame() == false) {
				EquipeB.equB.get(i).setX(xB + pion);
				EquipeB.equB.get(i).setY(yB - pion);

			}

		}

	}

	static void bougerAGauche(int i) {

		if (Hjoue == true) {
			System.out.println("bouger A gauche H");
			int xH = EquipeH.equH.get(i).getX();
			int yH = EquipeH.equH.get(i).getY();
			if (EquipeH.equH.get(i).getDame() == true) {
				ArrayList<Integer> liste1 = new ArrayList<Integer>();
				ArrayList<Integer> liste2 = new ArrayList<Integer>();

				for (int j = 1; j < 8; j++) {
					if (RechercheDameH(i, xH - pion * j, yH + pion * j) == Bouger
							&& xH - pion * j >= 0 && yH + pion * j <= 7 * pion) {
						liste1.add(j);
					}
					if (RechercheDameH(i, xH - pion * j, yH - pion * j) == Bouger
							&& xH - pion * j >= 0 && yH - pion * j >= 0) {
						liste2.add(j);
					}

				}
				int N = (int) (Math.random() * (liste1.size() + liste2.size()) + 1);
				if (N <= liste1.size()) {
					int j = liste1.get(N - 1);
					EquipeH.equH.get(i).setX(xH - pion * j);
					EquipeH.equH.get(i).setY(yH + pion * j);
				} else if (N > liste1.size()) {
					int j = liste2.get(N - 1 - liste1.size());
					EquipeH.equH.get(i).setX(xH - pion * j);
					EquipeH.equH.get(i).setY(yH - pion * j);
				}

			} else if (EquipeH.equH.get(i).getDame() == false) {
				EquipeH.equH.get(i).setX(xH - pion);
				EquipeH.equH.get(i).setY(yH + pion);
			}
		} else if (Hjoue == false) {
			System.out.println("bouger A gauche B");
			int xB = EquipeB.equB.get(i).getX();
			int yB = EquipeB.equB.get(i).getY();
			if (EquipeB.equB.get(i).getDame() == true) {
				ArrayList<Integer> liste1 = new ArrayList<Integer>();
				ArrayList<Integer> liste2 = new ArrayList<Integer>();

				for (int j = 1; j < 8; j++) {
					if (RechercheDameB(i, xB - pion * j, yB + pion * j) == Bouger
							&& xB - pion * j >= 0 && yB + pion * j <= 7 * pion) {
						liste1.add(j);
					}
					if (RechercheDameB(i, xB - pion * j, yB - pion * j) == Bouger
							&& xB - pion * j >= 0 && yB - pion * j >= 0) {
						liste2.add(j);
					}

				}

				int N = (int) (Math.random() * (liste1.size() + liste2.size()) + 1);
				System.out.println("N fin " + N);
				System.out.println("liste1 fin " + liste1);
				System.out.println("liste2 fin " + liste2);
				if (N <= liste1.size()) {
					int j = liste1.get(N - 1);
					System.out.println("j fin " + j);
					System.out.println("xB fin " + xB);
					System.out.println("yB fin " + yB);
					EquipeB.equB.get(i).setX(xB - pion * j);
					EquipeB.equB.get(i).setY(yB + pion * j);
				} else if (N > liste1.size()) {
					int j = liste2.get(N - 1 - liste1.size());
					EquipeB.equB.get(i).setX(xB - pion * j);
					EquipeB.equB.get(i).setY(yB - pion * j);
				}

			} else if (EquipeB.equB.get(i).getDame() == false) {
				EquipeB.equB.get(i).setX(xB - pion);
				EquipeB.equB.get(i).setY(yB - pion);
			}

		}

	}

	static boolean aucunHnepeutBouger() {
		for (int i = 0; i < EquipeH.equH.size(); i++) {
			if (peutBouger(i)) {
				return false;
			}
		}
		return true;
	}

	static boolean aucunBnepeutBouger() {
		for (int i = 0; i < EquipeB.equB.size(); i++) {
			if (peutBouger(i)) {
				return false;
			}
		}
		return true;
	}

	static void obligerAPrendre(int X, int Y) {
		X = (X / pion) * pion;
		Y = (Y / pion) * pion;

		int numPion = RecherchePionBH(X, Y);

		if (Hjoue == true) {
			if (!peutPrendre(numPion)) {
				for (int i = 0; i < EquipeH.equH.size(); i++) {
					if (i != numPion) {
						if (peutPrendre(i) == true) {
							JOptionPane
									.showMessageDialog(
											Echiquier.frame,
											"La prise des pions adverses est obligatoire",
											"  Attention !",
											JOptionPane.WARNING_MESSAGE);
							EteindreCase(numPion, X, Y);
							Case = false;
							break;

						}
					}
				}
			}

		} else if (Hjoue == false) {
			if (!peutPrendre(numPion)) {
				for (int i = 0; i < EquipeB.equB.size(); i++) {
					if (i != numPion) {
						if (peutPrendre(i) == true) {
							JOptionPane
									.showMessageDialog(
											Echiquier.frame,
											"La prise des pions adverses est obligatoire",
											"  Attention !",
											JOptionPane.WARNING_MESSAGE);
							EteindreCase(numPion, X, Y);
							Case = false;
							break;
						}
					}
				}
			}
		}
	}

	static void joueurAuto() {
		ArrayList<Integer> listePrendre = new ArrayList<Integer>();
		ArrayList<Integer> listeBouger = new ArrayList<Integer>();
		ArrayList<Integer> count = new ArrayList<Integer>();

		if (Hjoue == true) {

			for (int i = 0; i < EquipeH.equH.size(); i++) {
				if (peutPrendre(i)) {
					listePrendre.add(i);
				} else if (peutBouger(i)) {
					listeBouger.add(i);
				}
			}
			if (!listePrendre.isEmpty()) {
				count.clear();
				int N = (int) (Math.random() * (listePrendre.size()) + 1);

				if (peutPrendreADroite(listePrendre.get(N - 1))) {
					count.add(1);
				} else if (peutPrendreAGauche(listePrendre.get(N - 1))) {
					count.add(2);
				}
				int n = (int) (Math.random() * count.size() + 1);
				if (count.get(n - 1) == 1) {
					mangerADroite(listePrendre.get(N - 1));
					while (peutPrendreADroite(listePrendre.get(N - 1))) {
						mangerADroite(listePrendre.get(N - 1));
					}
					while (peutPrendreAGauche(listePrendre.get(N - 1))) {
						mangerAGauche(listePrendre.get(N - 1));
					}
				} else if (count.get(n - 1) == 2) {
					mangerAGauche(listePrendre.get(N - 1));
					while (peutPrendreADroite(listePrendre.get(N - 1))) {
						mangerADroite(listePrendre.get(N - 1));
					}
					while (peutPrendreAGauche(listePrendre.get(N - 1))) {
						mangerAGauche(listePrendre.get(N - 1));
					}
				}
				Dame(listePrendre.get(N - 1));
			} else if (!listeBouger.isEmpty()) {
				count.clear();

				int N = (int) (Math.random() * listeBouger.size() + 1);
				if (peutBougerADroite(listeBouger.get(N - 1))) {
					count.add(1);
				} else if (peutBougerAGauche(listeBouger.get(N - 1))) {
					count.add(2);
				}
				int n = (int) (Math.random() * count.size() + 1);
				if (count.get(n - 1) == 1)
					bougerADroite(listeBouger.get(N - 1));
				else if (count.get(n - 1) == 2)
					bougerAGauche(listeBouger.get(N - 1));
				Dame(listeBouger.get(N - 1));

			} else {
				System.out.println("Vous avez gagné");

				int result = JOptionPane.showConfirmDialog(null,
						"Vous avez gagné, voulez vous rejouer", "Jeu Dames 2A",
						JOptionPane.YES_NO_OPTION);
				if (result == 0) {// reponse oui
					new Echiquier();
					Hjoue = true;
					Case = false;
				} else
					System.exit(0);
			}
			Hjoue = false;
		} else if (Hjoue == false) {
			for (int i = 0; i < EquipeB.equB.size(); i++) {
				if (peutPrendre(i)) {
					listePrendre.add(i);
					System.out.println(i);
				} else if (peutBouger(i)) {
					listeBouger.add(i);
					System.out.println(i);
				}
			}
			System.out.println("listePrendre B : " + listePrendre.size());
			System.out.println("listeBouger B : " + listeBouger.size());
			if (!listePrendre.isEmpty()) {
				count.clear();
				int N = (int) (Math.random() * listePrendre.size() + 1);
				if (peutPrendreADroite(listePrendre.get(N - 1))) {
					count.add(1);
					System.out.println("B peut manger a droite");
				} else if (peutPrendreAGauche(listePrendre.get(N - 1))) {
					count.add(2);
					System.out.println("B peut manger a gauche");
				}
				System.out.println("count B : " + count.size());
				int n = (int) (Math.random() * count.size() + 1);
				if (count.get(n - 1) == 1) {
					mangerADroite(listePrendre.get(N - 1));
					while (peutPrendreADroite(listePrendre.get(N - 1))) {
						mangerADroite(listePrendre.get(N - 1));
					}
					while (peutPrendreAGauche(listePrendre.get(N - 1))) {
						mangerAGauche(listePrendre.get(N - 1));
					}
				} else if (count.get(n - 1) == 2) {
					mangerAGauche(listePrendre.get(N - 1));
					while (peutPrendreADroite(listePrendre.get(N - 1))) {
						mangerADroite(listePrendre.get(N - 1));
					}
					while (peutPrendreAGauche(listePrendre.get(N - 1))) {
						mangerAGauche(listePrendre.get(N - 1));
					}
				}
				Dame(listePrendre.get(N - 1));
			} else if (!listeBouger.isEmpty()) {
				count.clear();
				int N = (int) (Math.random() * listeBouger.size() + 1);
				System.out.println("N : " + N);
				if (peutBougerADroite(listeBouger.get(N - 1))) {
					count.add(1);
					System.out.println("B peut bouger a droite");
				}
				if (peutBougerAGauche(listeBouger.get(N - 1))) {
					count.add(2);
					System.out.println("B peut bouger a gauche");
				}
				System.out.println("count B : " + count.size());
				int n = (int) (Math.random() * count.size() + 1);
				if (count.get(n - 1) == 1)
					bougerADroite(listeBouger.get(N - 1));
				else if (count.get(n - 1) == 2)
					bougerAGauche(listeBouger.get(N - 1));
				Dame(listeBouger.get(N - 1));

			} else {
				System.out.println("Vous avez gagné");

				int result = JOptionPane.showConfirmDialog(null,
						"Vous avez gagné, voulez vous rejouer", "Jeu Dames 2A",
						JOptionPane.YES_NO_OPTION);
				if (result == 0) {// reponse oui
					new Echiquier();
					Hjoue = true;
					Case = false;
				} else
					System.exit(0);
			}
			Hjoue = true;
		}

		listePrendre.clear();
		listeBouger.clear();
	}

}
