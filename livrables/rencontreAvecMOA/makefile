all : compilRapport clean
compilRapport : 1.obde.teamflat.pdf

1.obde.teamflat.pdf : 1.obde.teamflat.tex
	pdflatex 1.obde.teamflat.tex && pdflatex 1.obde.teamflat.tex 
clean : 
	rm 1.obde.teamflat.aux 1.obde.teamflat.log 1.obde.teamflat.maf 1.obde.teamflat.mtc* 1.obde.teamflat.out 1.obde.teamflat.toc 1.obde.teamflat.tex.* 1.obde.teamflat.pyg